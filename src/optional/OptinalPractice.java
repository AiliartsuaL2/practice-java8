package optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptinalPractice {
    public static void main(String[] args) {
        /**
         * Optional
         * 장점
         * - null이 올 수 있는 값을 감싸는 Wrapper 클래스, 참조하더라도 NPE가 발생하지 않도록 도와줌,
         * - Optional 클래스는 value 필드에 값을 저장하기 때문에, 값이 null이더라도 바로 NPE가 발생하지 않고, 클래스이기 때문에 각종 메서드를 제공함
         *      >> 하지만 값의 존재 여부를 판단하지않고 접근한다면 NoSuchElementException이 발생함,,
         * - 내부에서 static 변수로 EMPTY 객체를 미리 생성해서 가지고 있어 빈 객체를 여러번 생성해줘야하는 경우에도, 1개의 EMPTY 객체를 공유함으로써 메모리를 절약함.
         *
         * 단점
         * - 값을 Wrapping하고, 다시 풀면서 null인경우 대체하는 함수를 호출하는등의 오버헤드가 있으므로 잘못 사용시 시스템 성능이 저하됨
         * - 따라서 메소드의 반환값이 절대 null이  아닌경우 Optional을 사용하지 않는것이 좋음. 즉 결과가 null이 될 수 있으며, null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용해야 함
         * Optional은 직렬화를 지원하지 않아 캐시나 메세지 큐등과 연동시 문제가 발생 할 수 있다. Jackson 라이브러리를 사용하면 wrap,unWrap하도록 지원해주기는 함,,
         *
         *  Optional 생성하기
         *      Optional은 Wrapper 클래스이기 때문에 값이 없을수도 있는데, 이 때는 Optional.empty()로 생성
         */
        Optional<String> optional = Optional.empty();

        System.out.println(optional);
        System.out.println(optional.isPresent());

        /**
         * Optional.of : 값이 Null이 절대로 아닌경우 Optional.of로 생성 할 수 있다.
         * 만약 Optional.of로 Null 저장시 , NPE 발생
         */
        Optional<String> optional2 = Optional.of("My Name");

        /**
         * Optional.ofNullbale() : 값이 null일 수도, 아닐수도 있는 경우에 사용하여 Optional 객체를 생성 할 수 있다.
         * 이후 orElse 또는 orElseGet 메소드를 이용하여 값이 없는 경우라도 안전하게 값을 가져올 수 있다.
         */
        List<String> nameList = Optional.ofNullable(getName()).orElseGet(() -> new ArrayList<>());

        Product product = new Product(13,"apple");
        Optional<String> optional3 = Optional.ofNullable(product.getName());
        String name = optional3.orElse("banana"); // 값이 없으면 banana 리턴

        /**
         * Optional API의 연산에는 orElse와 orElseGet 함수가 있음
         * 차이점
         * orElse : 파라미터로 값을 받는다. 값이 미리 존재하는 경우에 사용한다.
         * orElseGet : 파라미터로 함수형 인터페이스(함수)를 받는다. 값이 미리 존재하지 않는,, 거의 대부분의 경우에 orElseGet을 사용한다!
         *
         * orElse 파라미터에 함수에 리턴값으로 사용하기위해 메서드를 넣는다하면, 해당 함수가 실행되기 때문에 사용을 조심해야함,,
         * 또한 orElse는 값을 생성하여 orElseGet보다 비용이 크기 때문에 웬만하면 orElseGet을 사용!
         *
         */



    }
    public static List<String> getName(){
        return new ArrayList<>();
    }


}
