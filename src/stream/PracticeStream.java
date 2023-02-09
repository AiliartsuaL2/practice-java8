package stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class PracticeStream {
    public static void main(String[] args) {

        /**
         * Stream 연산들은 매개변수로 함수형 인터페이스를 받도록 되어있음.
         * 람다식은 반환값으로 함수형 인터페이스를 반환하고 있다.
         *
         * Stream API는 원본의 데이터를 조회하여 원본의 데이터가 아닌 별도의 요소들로 STream 생성, 단순히 원본 데이터를 읽기만 함, 정렬이나 필터링등의 작업은 별도의 Stream 요소들에서 처리가 된다.
         * Stream API는 일회용이기 때문에 한 번 사용이 끝나면 재사용이 불가능하다. >> Stream이 또 필요한 경우 Stream을 다시 생성해줘야 함, 닫힌 Stream 사용시, IllegalStateException 발생
         * Stream API는 메소드 내부에 반복 문법을 숨기고 있기 때문에 보다 간결한 코드의 작성이 가능하다.
         *
         *
         *         Todo 스트림 생성하기
         */

        /**
         * 배열을 스트림으로 전환
         */
        String[] arr = new String[]{"a","b","c"};
        Stream<String> arrStream = Arrays.stream(arr); // 배열을 stream으로 전환
        Stream<String> streamOfArrayPart = Arrays.stream(arr,1,3);// 1번부터 3번앞 까지

        /**
         * 컬렉션을 스트림으로 전환
         */
        List<String> arrList = asList(arr); // 배열 리스트로 전환
        Stream<String> listStream = arrList.stream(); // 리스트.stream()으로 스트림 생성

        /**
         * 비어있는 스트림 생성
         */
        Stream<String> emptyStream = streamOf(arrList); // 빈 스트림 생성

        /**
         * 빌더를 통한 스트림 생성
         */
        Stream<String> builderStream = Stream.<String>builder()
                .add("abc").add("bcd").add("ec")
                .build();

        /**
         * Stream.generate()를 이용한 스트림 생성
         * Supplier<T>에 해당하는 람다로 값을 넣을 수 있다.(인자가 없고 리턴값만 있는 함수형 인터페이스)
         * 따로 크기가 정해져있지 않기 때문에 리밋을 걸어둬야 함
         */
        Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5); // 5개의 gen이 들어간 스트림이 생성됨.

        /**
         * Stream.iterate()를 이용한 스트림 생성
         * 람다를 이용해 스트림에 들어갈 요소를 만듬.
         * 따로 크기가 정해져있지 않기 때문에 리밋을 걸어둬야 함
         */
        Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5); // 30부터 2씩 증가하는 숫자 5개가 들어간 스트림이 생성됨

        /**
         * 기본 타입형 스트림
         * 제네릭을 사용하지 않기 때문에 오토박싱이 일어나지 않음
         */
        IntStream intStream = IntStream.range(1, 5); // 1,2,3,4 스트림 생성
        LongStream longStream = LongStream.rangeClosed(1, 5);// 1,2,3,4,5 스트림 생성

        /**
         * boxed 메서드를 이용한 박싱처리도 가능
         */
        Stream<Integer> boxedStream = intStream.boxed();

        /**
         * Stream.concat 메서드를 이용하여 두 개의 스트림을 연결하여 새로운 스트림을 만들 수 있다.
         */
        Stream<String> concatStream = Stream.concat(listStream, builderStream);


        /**
         *         Todo 스트림 가공하기
         */

        List<String> names = asList("Eric", "Elena", "Jav");

        /**
         * Filtering
         * 인자로 받는 Predicate는 boolean을 리턴해주는 함수형 인터페이스로 평가식이 들어간다.
         */
        names.stream().filter(name -> name.contains("a")); // a가 들어간 이름만 스트림이 리턴

        /**
         * Mapping
         * 값을 변환하기위한 람다를 인자로 받는다.
         * 스트림 요소들이 input되고 람다를 거쳐 output이 새로운 스트림에 담긴다.
         */
        Stream<String> bigNames = names.stream().map(String::toUpperCase); // 대문자로 변환한 값들이 담긴 스트림으로 리턴

        /**
         * Sorting
         * 다른 소팅과 마찬가지로 Comparator을 사용함
         * 인자없이 넘기면 오름차순으로 정렬 , 원하는 정렬 방법을 Comparator처럼 정의 가능
         */
        List<Integer> sortedList = IntStream.of(14, 11, 20, 39, 23)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        List<String> sortList = names.stream()
                .sorted((s1, s2) -> s2.length() - s1.length())
                .collect(Collectors.toList()); // 글자수 내림차순 정렬

        /**
         * Iterating
         * peek 메서드를 활용하여 stream 내 요소들 각각을 대상으로 특정 연산을 수행함,
         * 특정 결과를 반환하지않는 함수형 인터페이스인 Consumer를 인자로 받는다.
         */
//        IntStream.of(1,3,4,5,6,7)
//                .peek(System.out::println)
//                .sum();


        /**
         *         Todo 스트림 결과 만들기
         */
        /**
         * Calculating : 값들을 기본형이나 Optional타입으로 결과를 만듬
         * sum, count는 primitive타입 (int,double,long 등)으로 리턴
         * average,min,max는 스트림이 비어있는경우 값이 없기 때문에 Optional타입으로 리턴
         * 스트림에서 ifPresent 메소드를 이용하여 Optional 처리를 할 수 있다.
         */
        int sum = IntStream.of(1, 3, 4, 5, 6, 7, 8).sum();

        long count = IntStream.of(1, 3, 4, 5, 6, 7, 8).count();

        OptionalDouble optAvg = IntStream.of(1, 3, 4, 5, 6, 7, 8).average();
        OptionalInt optMin = IntStream.of(1, 3, 4, 5, 6, 7, 8).min();
        OptionalInt optMax = IntStream.of(1, 3, 4, 5, 6, 7, 8).max();

        IntStream.of(1, 3, 4, 5, 6, 7, 8)
                .average()
                .ifPresent(System.out::println); // 값이 존재하면 sout

        /**
         * Reduction
         * reduce라는 메서드를 이용하여 결과를 만들 수 있다.
         * accumulator : 각 요소를 처리하는 계산 로직. 각 요소가 올 떄마다 중간 결과를 생성하는 로직
         * identity : 계산을 위한 초기값으로 스트림이 비어서 계산할 내용이 없더라도 이 값은 리턴,,
         * combiner : 병렬 스트림에 나눠 계산한 결과르 하나로 합치는 동작을 하는 로직, 병렬 스트림에서만 동작
         * 인자가 1개, 2개, 3개인경우로 각각 메소드가 오버로딩되어 다름.
         */

        /**
         * 인자가 1개인 reduce
         * parameter : BinaryOperator(같은타입 인자 2개를 받아 같은 타입의 결과를 반환) accumulator
         */
        OptionalInt reduce =
                IntStream.range(1, 4)
                .reduce((a, b) -> {
                    return Integer.sum(a, b);
                });
        /**
         * 인자가 2개인 reduce
         * parameter : identity , BinaryOperator accumulator
         */
        int reducedTwoParams = IntStream.range(1, 4)
                .reduce(10, Integer::sum);

        /**
         * 인자가 3개인 reduce
         * parameter : identity, BinaryOperator accumulator , BinaryOperator combiner
         */
        Stream.of(1,2,3)
                .reduce(10, // identity
                        Integer::sum, // accumulator
                        (a,b) ->{
                            System.out.println("combiner was called");
                            return a+b;
                        }); //병렬 스트림이 아니기 때문에 컴바이너 동작 안함,

        /**
         * Collecting : Collector 타입의 인자를 받아서 처리, 자주 사용하는 작업은 Collectors 객체에서 제공
         * Collectors.toList : 스트림 작업 결과를 담은 리스트로 반환
         */
        List<String> aList = new ArrayList<>();
        aList.add("abcd");
        aList.add("efgd");
        aList.add("hujd");

        List<String> ansList = aList.stream() // 스트림 생성
                .map(String::toUpperCase) // 스트림 가공
                .collect(Collectors.toList()); // 스트림 결과처리

        /**
         * Collectors.joining() : 스트림에서 작업한 결과를 하나의 스트링으로 이어 붙인다.
         * parameter (Optional)
         *  - delimiter : 각 요소 중간에 들어가 요소를 구분시켜주는 구분자 , 독립적으로 사용 가능
         *  - prefix : 결과 맨 앞에 붙는 문자, delimiter , suffix와 세트
         *  - suffix : 결과 맨 뒤에 붙는 문자, delimiter , preffix와 세트
         */
        String collect = aList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining());
        String collect2 = aList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(",","<",">"));

        /**
         * Collectors.averageingInt() : 숫자값의 평균을 낸다.
         * Collectors.summingInt() : 숫자값의 합을 낸다
         * Collectors.IntSummaryStatistics : 개수, 합, 최소, 최대, 평균값이 들어가는 객체
         *
         */
        List<Product> productList =
                Arrays.asList(new Product(23, "potatoes"),
                        new Product(14, "orange"),
                        new Product(13, "lemon"),
                        new Product(23, "bread"),
                        new Product(13, "sugar"));

        Double averageAmount = productList.stream()
                .collect(Collectors.averagingInt(Product::getAmount));

        Integer summingAmount = productList.stream()
                .collect(Collectors.summingInt(Product::getAmount));

        long summarySum = productList.stream()
                .collect(Collectors.summarizingInt(Product::getAmount)).getSum();
        double summaryAvg = productList.stream()
                .collect(Collectors.summarizingInt(Product::getAmount)).getAverage();
        int summaryMax = productList.stream()
                .collect(Collectors.summarizingInt(Product::getAmount)).getMax();
        int summaryMin = productList.stream()
                .collect(Collectors.summarizingInt(Product::getAmount)).getMin();
        long summaryCnt = productList.stream()
                .collect(Collectors.summarizingInt(Product::getAmount)).getCount();

        /**
         * Matching : 조건식 람다 Predicate를 받아 해당 조건을 만족하는 요소가 있는지 체크한 결과를 리턴한다.
         * anyMatch : 하나라도 조건을 만족하는지
         * allMatch : 모든 조건을 만족하는지
         * noneMatch : 모든 조건을 불만족 하는지
         */
        boolean anyMatch = names.stream()
                .anyMatch(name -> name.contains("a"));
        boolean allMatch = names.stream()
                .allMatch(name -> name.length() > 3);
        boolean noneMatch = names.stream()
                .noneMatch(name -> name.endsWith("s"));

        /**
         * Iterating : forEach를 통해 요소를 돌며 실행시키는 최종 작업
         * peek와는 중간 작업과 최종 작업이라는 차이점이 있다.
         */
        names.stream().forEach(System.out::println);
    }


    /**
     * 빈 스트림 생성 메서드 예시,
     */
    public static Stream<String> streamOf(List<String> list){
        return list == null || list.isEmpty()  // 비어있는경우
                ? Stream.empty() // 빈스트림 생성
                : list.stream(); // 안비어있으면 리스트의 스트림 출력
    }

}
