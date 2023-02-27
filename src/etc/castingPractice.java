package etc;

import optional.Product;

public class castingPractice {
    public static void main(String[] args) {

        /**
         * 강제 형변환 - (String)
         * Object가 실제로 String인경우에만 가능함
         * ClassCastException 발생
         */

        Object notString = new Integer(42);
//        String notString1 = (String) notString;
//        System.out.println("notString1 = " + notString1);

        Object realString = "hello World";
        String realString1 = (String) realString;
        System.out.println("realString1 = " + realString1);

        /**
         * Obj.toString()
         * Object의 메소드이므로 Wrapper 클래스의 데이터를 String으로 형변환 시켜줌
         * primitive Type은 형변환 불가능,
         * Object가 null인경우, NPE발생
         */
        int intStr = 32;
        //intStr.toString(); // int형에는 toString 메서드 자체가 없기 때문에 성립이 안됨


        Object notString2 = new Integer(42);
        String toStr = notString2.toString();
        System.out.println("toStr = " + toStr);


        /**
         * String.valueOf 사용시 어떠한 값을 넣어도
         * 안전하게 형변환 가능
         * 빈 문자열에 더하는경우 해당 방식으로 해당 방식으로 캐스팅 해주는것이 좋고,
         * 스트링 문자열에 더하는 경우에는 그냥 더해줘도 컴파일러가 String으로 인식하기 때문에 그냥 더해주는것이 좋다.
         * Object가 null인경우, "null" 문자열 생성
         */

        Object notString3 = new Integer(42);
        String s = String.valueOf(notString3);
        System.out.println("s = " + s);


        Object nullString = null;
        String nullStr = String.valueOf(nullString);
        System.out.println("nullStr = " + nullStr);

        /**
         * instanceof : 참조변수가 참조하고있는 인스턴스의 실제 타입을 알아보는 연산자,,
         *   - null 참조시 false 반환(첫번째 연산임)
         *   - 어떤 타입에 대한 instanceof 연산의 결과가 true인 경우, 검사한 타입으로 형변환이 가능함.
         *   - 객체 instanceof 클래스 형태로 연산진행
         */
        Product product = new Product(5, "apple");
        Object o = null;
        System.out.println(o instanceof Product);
        System.out.println(product instanceof Product);


    }
}
