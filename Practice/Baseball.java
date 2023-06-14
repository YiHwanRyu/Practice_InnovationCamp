package Practice;

import java.util.Scanner;

public class Baseball {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] computer = new int[3];
        int n = 0;
        // 중복제거하여 랜덤숫자 배열
        for (int i = 0 ; i < computer.length; i++) {
            computer[i] = (int)(Math.random() * 10);
            for (int j = 0; j < i; j++) {
                if(computer[i] == computer[j]) {
                    i--;
                    break;
                }
            }
        }
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        while(true) {
            n++;
            System.out.print(n + "번째 시도 : ");
            String player_string = scanner.nextLine();
            char[] player = player_string.toCharArray();
            if (player.length != 3) {
                System.out.println("잘못된 입력입니다! 다시 입력해주세요!(시도 횟수는 증가하지 않습니다.)");
                n--;
                continue;
            }
            int s = 0;
            int b = 0;
            for(int i = 0; i < 3; i++) {
                if (computer[i] == (int)player[i] - (int)'0') {
                    s++;
                } else {
                    for (int j = 0; j < 3; j++) {
                        if (computer[j] == (int)player[i] - (int)'0') {
                            b++;
                        }
                    }
                }
            }
            if (s == 3) {
                System.out.println("3S");
                System.out.println(n + "번만에 맞히셨습니다.\n게임을 종료합니다.");
                break;
            } else if (b == 3) {
                System.out.println("3B");
            } else {
                System.out.println(b + "B" + s + "S");
            }

        }
    }
}
