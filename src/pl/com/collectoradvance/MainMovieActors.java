package pl.com.collectoradvance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMovieActors {

	public static void main(String[] args) throws IOException {
		
		Set<Movie> movies = new HashSet<>();
		
		Stream<String> lines 
				= Files.lines(
						Paths.get("files/movies-mpaa.txt")
				);
		
		
		lines.forEach(
				(String line) -> {
					String[] elements = line.split("/");
					String title =
							elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
					String releaseYear =
							elements[0].substring(elements[0].lastIndexOf("(") + 1, elements[0].lastIndexOf(")"));
				
					if (releaseYear.contains(",")) {
						return;
					}
					
					Movie movie = new Movie(title, Integer.valueOf(releaseYear));
					
					for	(int i = 1; i < elements.length; i++){
						String[] name = elements[i].split(", ");
						String lastName = name[0].trim();
						String firstName = "";
						if(name.length > 1){
							firstName = name[1].trim();
						}
						
						Actor actor = new Actor(lastName, firstName);
						movie.addActor(actor);
					}
					
					movies.add(movie);
				
				}
		
		);
		System.out.println("Movies = "+movies.size());
		
		//numbers of actors
//		movies.stream()
//			.flatMap(movie -> movie.actors().stream()) //stream<Set<Actors>> po dodaniu drugiego stream i flat Map mamy Stream<Actors>
//			.collect(Collectors.toSet())
//			.size();
// 		mozna napisac wydajniej:
		long numberOfActors = movies.stream()
		.flatMap(movie -> movie.actors().stream()) //stream<Set<Actors>> po dodaniu drugiego stream i flat Map mamy Stream<Actors>
		.distinct()
		.count();
		
		System.out.println("numberOfActors: "+numberOfActors);
		
		//number of actor that played in the most movies
		Map.Entry<Actor, Long> mostViewedActor = movies.stream()
				.flatMap(movie -> movie.actors().stream())
				.collect(Collectors.groupingBy(
						actor -> actor,
						Collectors.counting()
						)
				)
				.entrySet().stream() //Stream<Map.Entry<Actor, Long>>
				.max(
//						Comparator.comparing(entry -> entry.getValue())
						Map.Entry.comparingByValue()//better
				)
				.get();
		
		System.out.println("The most viewed actor is: "+mostViewedActor);
		
		//actor that played in the greatest number of movies during a year
		//Map<release years, Map<Actor, # of movies during that year>>
//		Map<Integer, HashMap<Actor, AtomicLong>> collect =
		Entry<Integer, Entry<Actor, AtomicLong>> get = 
		movies.stream()
			  .collect(
					Collectors.groupingBy(
						movie -> movie.releaseYear(),
						Collector.of(
								() -> new HashMap<Actor, AtomicLong>(),//supplier
								(map, movie) -> {
									movie.actors().forEach(
											actor -> map.computeIfAbsent(actor, a -> new AtomicLong()).incrementAndGet()
									);
								},//accumulator
								
								(map1, map2) -> {
									map2.entrySet().forEach(
											entry2 -> map1.merge(
													entry2.getKey(), entry2.getValue(),
													(al1, al2) -> {
														al1.addAndGet(al2.get());
														return al1;
													}
													
											)
									);
									return map1;
								},//combiner
								Collector.Characteristics.IDENTITY_FINISH
						)
					)
		)
		//Map<Integer, HashMap<Actor, AtomicLong>> 
		.entrySet().stream()
		.collect(
				Collectors.toMap(
						entry -> entry.getKey(),
						entry -> entry.getValue().entrySet().stream()
								.max(
										Map.Entry.comparingByValue(Comparator.comparing(l -> l.get()))
								)
								.get()
				
				)
		)
		.entrySet().stream()
		.max(
				Map.Entry.comparingByValue(
						Comparator.comparing(
								entry -> entry.getValue().get()
						)
				)
				
		)
		.get();
		
		System.out.println("Get: "+get);
		
		//Map<Integer, Map.Entry<Actor, AtomicLong>> 
	}

}
