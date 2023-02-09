package lambda;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.util.Objects;
import java.util.function.*;

public class LambdaPractice {

    /**
     * 람다함수 : 익명 함수를 지칭하고, 함수를 보다 단순하게 표현하는 방법
     *      - 지연 연산을 통해 불필요한 연산을 최소화
     *      - 멀티 쓰레드를 활용하여 병렬처리를 사용 가능
     *      - 람다식은 함수형 인터페이스에서 사용한다.
     *      - 람다식 내에서 사용되는 지역변수는 final이 붙지 않아도 상수로 간주..
     *
     *
     * 람다 표현식 :
     *      1. (매개변수) -> 함수몸체 형태
     *      2. 함수 몸체가 단일 실행문이면, 중괄호 {}를 생략 가능
     *      3. 함수 몸체가 return문으로만 구성시, 생략이 불가능함
     *
     * 함수형 인터페이스 : 구현해야 할 추상 메서드가 하나만 정의된 인터페이스
     *      - @FunctionalInterface 어노테이션은 기능은 없지만, 2가지 이상의 추상 메서드가 있는경우, 컴파일단에서 에러를 발생시킴
     *
     *
     */
    public static void main(String[] args) {
        Math plusLambda = (first , second) -> first+second; // 추상 메소드를 람다로 구현
        System.out.println("plusLambda = " + plusLambda.Calc(4,2));

        Math minusLambda = (first, second) -> first-second;
        System.out.println("minusLambda = " + minusLambda.Calc(4,2));

        /**
         * Java에서 지원하는 java.util.function 인터페이스
         * IntFunction<R> : int값의 인수를 받아들이고, 결과를 생성해내는 함수
         * BinaryOperator<T> : 동일한 유형의 두 피연산자에 대한 연산을 나타내며, 피연산자와 동일한 유형의 결과를 생성한다.
         */

        BinaryOperator stringSum = (x,y)->x+" "+y;
        System.out.println("stringSum.= " + stringSum.apply("Hello","World"));

        /**
         * 매개변수가 없고, 리턴값이 없는 람다식
         */


        /**
         * 매개변수가 있고, 리턴값이 없는 함수형 인터페이스 - Consumer<T>
         * void accept(T t)를 추상 메소드로 갖는다.
         * andThen이라는 함수 제공, 하나의 함수가 끝난 후 다음 Consumer를 연쇄적으로 이용 할 수 있다.
         */
        Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[0]);
        consumer.andThen(System.out::println).accept("Hello123 World");


        /**
         * 매개변수가 없고, 리턴값이 있는 함수형 인터페이스 - Supplier<T>
         * T get()을 추상 메소드로 갖고있다.
         */
        Supplier<String> supplier = () -> "Hello World!";
        System.out.println(supplier.get());


        /** 매개변수가 있고, 리턴값이 있는 함수형 인터페이스 -  Operator , Predicate, Function
         * 으로 나눌 수 있음,
        */

        /**
         * 매개변수가 있고, 리턴값이 있는 함수형 인터페이스 - Function<T,R>
         * 객체 T를 매개변수로 받아 처리한 후 R로 반환하는 함수형 인터페이스
         * andThen 제공, 추가적으로 compose 제공,
         * andThen은 첫 번째 함수가 실행된 이후 다음 함수를 연쇄적으로 실행하도록 연결 되어있음
         * compose는 첫 번째 함수 실행 이전에, 먼저 함수를 실행하여 연쇄적으로 연결해준다.
         * identity : 자기 자신을 반환하는 static 함수
         */
        Function<String , Integer> function = str -> str.length();
        Integer strLen = function.apply("Hello World");

        /**
         * 매개변수가 있고, 리턴값이 boolean인 함수형 인터페이스 - Predicate<T>
         * Boolean test(T t)를 추상 메소드로 갖고있음
         */
        Predicate<String> predicate = (str) -> str.equals("Hello World!");
        boolean preTest = predicate.test("Hello World!");
        System.out.println("preTest = " + preTest);

        /**
         * 메소드 참조 : 함수형 인터페이스를 람다식이 아닌, 일반 메소드를 참조시켜 선언하는 방법
         * 일반 메소드를 참조하기 위한 3가지 조건
         *      - 함수형 인터페이스의 매개변수 타입 = 참조하려는 메소드의 매개변수 타입
         *      - 함수형 인터페이스의 매개변수 개수 = 참조하려는 메소드의 매개변수 개수
         *      - 함수형 인터페이스의 반환형 = 참조하려는 메소드의 반환형
         * 참조하려는 메소드는 일반메소드, Static 메소드, 생성자가 있고, 클래스이름::메소드이름 으로 참조 할 수 있다.
         * 함수형 인터페이스로 반환이 된다.
         */

        /**
         * 일반 메소드 참조
         * 구현하는 메소드에 따라 함수형 인터페이스가 결정된다.
         */
        Function<String, Integer> function1 = String::length; // 참조하는 메서드에 따라 함수형 인터페이스가 결정
        function1.apply("Hello World"); // String.length는 매개변수가 있고(String), 리턴값이 있기(Integer) 때문에 Function타입

        Consumer sysOut = System.out::println; // sout은 매개변수가 있고(String), 리턴값이 없기(void) 때문에 Consumer
        sysOut.accept("heeeelllloo");

        /**
         * Static 메소드 참조
         */
        Predicate<Boolean> isNull = Objects::isNull;

        /**
         * 생성자 참조
         * 생성자는 new로 생성해주기 때문에 클래스이름::new로 참조 가능,
         * Supplier는 매개변수가 없이 반환값만 갖는 인터페이스이기 때문에, 매개변수 없는 생성자는 Supplier로 생성 가능
         */
        Supplier<String> stringSupplier = String::new;

    }


    /**
     * Static 메소드 참조
     * @param obj
     * @return boolean
     */
    public static boolean isNull(Object obj){
        return obj == null;
    }




}
