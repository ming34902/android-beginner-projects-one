package derry.s2;

// 接口折中方案，来替换 kotlin中的lambda表达式问题
interface IsShowResult {
    void result(String result);
}

public class KTBase34 {

    public static void main(String[] args) {
        // 匿名函数-匿名函数接口实现
        showPersonInfo("李四", 22, '男', new IsShowResult() {
            @Override
            public void  result(String result) {
                System.out.println("显示结果："+ result);
            }
        });

        // 具名函数 showResultImpl
        IsShowResult showResultImpl = new MShowResultImpl();
        showPersonInfo("王五",89, '男', showResultImpl);
    }


    static class MShowResultImpl implements IsShowResult{
        @Override
        public void result(String result) {
            System.out.println("显示结果："+ result);
        }
    }


    static void showPersonInfo(String name, int age, char sex, IsShowResult isShowResult) {
        String str = String.format("name:%s,age:%d,sex:%c", name, age, sex);
        isShowResult.result(str);
    }

}
