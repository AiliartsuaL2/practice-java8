package lambda;


@FunctionalInterface // 추가적인 기능은 없지만, 추상 메서드가 2개 이상인경우 컴파일단에서 오류를 잡아주기 때문에 선언해주는것이 좋음
public interface Math {
    public int Calc(int first, int Second);
    // public int Calc2(int first, int Second); // 이렇게 메서드가 2개 이상이면 컴파일단에서 에러 생김
}
