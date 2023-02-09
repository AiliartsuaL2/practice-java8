package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PracticeStream {
    public static void main(String[] args) {
        /**
         * 배열을 스트림으로 전환
         */
        String[] arr = new String[]{"a","b","c"};
        Stream<String> arrStream = Arrays.stream(arr); // 배열을 stream으로 전환
        Stream<String> streamOfArrayPart = Arrays.stream(arr,1,3);// 1번부터 3번앞 까지

        /**
         * 컬렉션을 스트림으로 전환
         */
        List<String> arrList = Arrays.asList(arr); // 배열 리스트로 전환
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
