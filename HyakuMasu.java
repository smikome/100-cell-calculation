//百マス計算
import java.util.Random;
import java.io.*;

public class HyakuMasu{
	public static void main(String[] args){
		//横軸と縦軸に与えられる数字の個数
		final int yoko = 10;
		final int tate = 10;
		//与えられる数字の範囲（10なら0から9まで）
		final int rangeOfNum = 10;
		//足し算なのか掛け算なのか(trueなら足し算をやる)
		boolean plus;
		//正解数
		int correctNum = 0;
		
		//与えられる横軸の数字と縦軸の数字を格納する配列
		int yokoNum[] = new int[yoko];
		int tateNum[] = new int[tate];
		//数字を初期化
		Random rnd = new Random();
		//足し算か掛け算か
		int plusOrTimes = rnd.nextInt() % 2;
		if(plusOrTimes < 0){
			//負の整数だったら正の整数に直す
			plusOrTimes = -plusOrTimes;
		}
		if(plusOrTimes == 0){
			plus = true;
		}else{
			plus = false;
		}
		//横軸の数字を適当に決める
		for(int i = 0; i < yoko; i++){
			yokoNum[i] = rnd.nextInt() % rangeOfNum;
			//負の整数だったら正の整数に直す
			if(yokoNum[i] < 0){
				yokoNum[i] = -yokoNum[i];
			}
		}
		//縦軸の数字を適当に決める
		for(int i = 0; i < tate; i++){
			tateNum[i] = rnd.nextInt() % rangeOfNum;
			//負の整数だったら正の整数に直す
			if(tateNum[i] < 0){
				tateNum[i] = -tateNum[i];
			}
		}
		//答え
		int answer[][] = new int[yoko][tate];
		for(int i = 0; i < yoko; i++){
			for(int j = 0; j < tate; j++){
				if(plus){
					answer[i][j] = yokoNum[i] + tateNum[j];
				}else{
					answer[i][j] = yokoNum[i] * tateNum[j];
				}
			}
		}
		//解答したかどうか(初期値false)
		boolean isAnswered[][] = new boolean[yoko][tate];
		for(int i = 0; i < yoko; i++){
			for(int j = 0; j < tate; j++){
				isAnswered[i][j] = false;
			}
		}
		//正解したかどうか(初期値false)
		boolean isCorrect[][] = new boolean[yoko][tate];
		for(int i = 0; i < yoko; i++){
			for(int j = 0; j < tate; j++){
				isCorrect[i][j] = false;
			}
		}

		//入力用
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("百マス計算にようこそ。");
		for(int i = 0; i < tate; i++){
			for(int j = 0; j < yoko; j++){
				printHyakumasu(tateNum, yokoNum, answer, isAnswered, isCorrect);
				if(plus){
					System.out.println(tateNum[i] + "+" + yokoNum[j] + "=?");
				}else{
					System.out.println(tateNum[i] + "*" + yokoNum[j] + "=?");
				}
				System.out.print("->");
				//解いている人の答え
				String yourAnswer = "";
				while(true){
					try{
						yourAnswer = scan.readLine();
					}catch(IOException e){
						System.out.print(e);
					}
					//入力した文字が数字だった場合
					if(isNumber(yourAnswer)){
						//解答済みにする
						isAnswered[j][i] = true;
						//答えがあっていたら
						if(Integer.parseInt(yourAnswer) == answer[j][i]){
							isCorrect[j][i] = true;
							System.out.println("正解！");
							correctNum++;
						}else{//答えが間違っていたら
							isCorrect[j][i] = false;
							System.out.println("間違いです。");
							System.out.println(tateNum[i] + "+" + yokoNum[j] + "=" + answer[j][i] + "です。");
						}
						//while文を抜ける
						break;
					}else{
						System.out.print("数字を入力してください。->");
					}
				}
			}
		}
		printHyakumasu(tateNum, yokoNum, answer, isAnswered, isCorrect);
		System.out.println("正解数は" + correctNum + "/100です。");
		System.out.println("お疲れ様でした。");
	}
	static void printHyakumasu(int[] tNum, int[] yNum, int[][] ans, boolean[][] isAns, boolean[][] isCor){
		//半角スペース2個
		System.out.print("  ");
		//横軸の数字を羅列
		for(int i = 0; i < yNum.length; i++){
			System.out.print("| " + yNum[i] + " ");
		}
		System.out.println("|");
		for(int i = 0; i < tNum.length; i++){
			//縦軸の数字を出力
			System.out.print(" " + tNum[i] + "|");
			//答えを出力
			for(int j = 0; j < yNum.length; j++){
				//解答済みなら
				if(isAns[j][i]){
					//２桁の数字なら
					if(ans[j][i] >= 10){
						if(isCor[j][i]){	//正解
							System.out.print(ans[j][i] + "o|");
						}else{	//不正解
							System.out.print(ans[j][i] + "x|");
						}
					}else{	//１桁の数字なら
						if(isCor[j][i]){
							System.out.print(" " + ans[j][i] + "o|");
						}else{
							System.out.print(" " + ans[j][i] + "x|");
						}
					}
				}else{
					System.out.print("__ |");
				}
			}
			//改行
			System.out.println("");
		}
	}
	//文字列が数字かどうかを判別する関数
	public static boolean isNumber(String num){
		try{
			Integer.parseInt(num);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
}