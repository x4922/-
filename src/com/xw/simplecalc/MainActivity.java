package com.xw.simplecalc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private Button ac;
	private String numStr="0";
	private String ACtemp="0";
	private int dptemp=0;
	protected ArrayList<String> list =new ArrayList<String>();
	private String[] Operators={"+","-","*","/"};
	private String[] FirstOperators={"*","/"};
	private String[] SecondOperators={"+","-"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.vertical_layout);
		list.add("0");
		Button zero=(Button) findViewById(R.id.zero);
		zero.setOnClickListener(this);
		Button one=(Button) findViewById(R.id.one);
		one.setOnClickListener(this);
		Button two=(Button) findViewById(R.id.two);
		two.setOnClickListener(this);
		Button three=(Button) findViewById(R.id.three);
		three.setOnClickListener(this);
		Button four=(Button) findViewById(R.id.four);
		four.setOnClickListener(this);
		Button five=(Button) findViewById(R.id.five);
		five.setOnClickListener(this);
		Button six=(Button) findViewById(R.id.six);
		six.setOnClickListener(this);
		Button seven=(Button) findViewById(R.id.seven);
		seven.setOnClickListener(this);
		Button eight=(Button) findViewById(R.id.eight);
		eight.setOnClickListener(this);
		Button nine=(Button) findViewById(R.id.nine);
		nine.setOnClickListener(this);
		Button equal=(Button) findViewById(R.id.equal);
		equal.setOnClickListener(this);
		Button add=(Button) findViewById(R.id.add);
		add.setOnClickListener(this);
		Button minus =(Button) findViewById(R.id.minus);
		minus.setOnClickListener(this);
		Button dpoint=(Button)findViewById(R.id.decimal_point);
		dpoint.setOnClickListener(this);
		Button multiply=(Button)findViewById(R.id.multiply);
		multiply.setOnClickListener(this);
		Button per_cent=(Button) findViewById(R.id.per_cent);
		per_cent.setOnClickListener(this);
		ac=(Button) findViewById(R.id.AC);
		ac.setOnClickListener(this);
		Button plus_minus=(Button) findViewById(R.id.plus_minus);
		plus_minus.setOnClickListener(this);
		Button except=(Button) findViewById(R.id.except);
		except.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zero:
			getNumstr("0");
			break;
		case R.id.one:
			getNumstr("1");
			break;
		case R.id.two:
			getNumstr("2");
			break;
		case R.id.three:
			getNumstr("3");
			break;
		case R.id.four:
			getNumstr("4");
			break;
		case R.id.five:
			getNumstr("5");
			break;
		case R.id.six:
			getNumstr("6");
			break;
		case R.id.seven:
			getNumstr("7");
			break;
		case R.id.eight:
			getNumstr("8");
			break;
		case R.id.nine:
			getNumstr("9");
			break;
		case R.id.plus_minus:
			getPlus_minus();
			break;
		case R.id.decimal_point:
			if(dptemp==0){
				getNumstr(".");
				dptemp=1;
			}
			break;	
		case R.id.except:
			getOperator("/");
			break;	
		case R.id.multiply:
			getOperator("*");
			break;	
		case R.id.minus:
			getOperator("-");
			break;	
		case R.id.add:
			getOperator("+");
			break;	
		case R.id.AC://清除
			if(ac.getText().equals("AC")){
				remove();
			}
			else {
				clear();
			}
			break;
		case R.id.per_cent://百分号
			per_cent();
			break;
		case R.id.equal://等于
			equal();
			break;		
		default:
			break;
		}
	}
	public void getNumstr(String numstr) {//获得输入的数字
		int num;
		String minus="";
		ACtemp="1";
		if(list.size()==0){
			list.add("0");
		}
		if (findLastElement()) {
			list.add("0");
		}
		if(numStr.indexOf("-")!=-1){
			numStr=numStr.substring(1);
			minus="-";
		}
		if(numStr.indexOf(".")!=-1){
			num=10;
		}else {
			num=9;
		}
		if(numStr.length()<num){
			if(numStr.equals("0")){
				if(numStr.indexOf(".")!=-1){
					numStr+=numstr;
				}
				else if(numstr.equals(".")){
					numStr+=numstr;
				}else{
					numStr=numstr;
				}
				numStr=minus+numStr;
				list.set(list.size()-1,numStr);
			}else {
				numStr += numstr;
				numStr=minus+numStr;
				list.set(list.size()-1,numStr);
			}
		}
		display(numStr, list);
		Log.d("calc",list.toString());
	}
	public void getOperator(String string) {//获得符号
		ACtemp="1";
		dptemp=0;
		if(list.size()==0){
			list.add("0");
		}
		String result;
		if(findLastElement()){//判断最后一位是否为符号
			list.set(list.size()-1,string);
			numStr="0";
		}
		else{
			list.add(string);
			numStr="0";
		}
			result=firstoperating();
			display(result,list);
		Log.d("calc", list.toString());
	}
	public Boolean findLastElement(){
		Boolean temp=false;
		if(findListOperators(list.get(list.size()-1), Operators)){
			temp=true;
		}
		return temp;
	}
	public Boolean findListOperators(String string,String[] c){
		Boolean temp=false;
		for(String string1:c){
			if(string1.equals(string)){
				temp=true;
				break;
			}
		}
		return temp;
	}
	public void getPlus_minus() {//正负号
		if(list.size()==0){
			list.add("0");
		}
		if (findLastElement()) {
			list.add("0");
		}
		if(numStr.indexOf("-")==-1){
			numStr = "-"+numStr;
			list.set(list.size()-1,numStr);
			display(numStr,list);
		}
		else{
			numStr = numStr.substring(1);
			list.set(list.size()-1,numStr);
			display(numStr,list);
		}
	}
	public void per_cent() {//百分号
		BigDecimal numStr1=new BigDecimal(numStr);
		BigDecimal per_cent=new BigDecimal("100");
		numStr=numStr1.divide(per_cent).stripTrailingZeros().toString();
		list.set(list.size()-1,numStr);
		display(numStr,list);
	}
	public void clear() {
		ACtemp="0";
		numStr="0";
		dptemp=0;
		display(numStr, list);
		Log.d("calc", numStr);
	}
	public void remove() {
		list.removeAll(list);
		Log.d("calc", list.toString());
		display(numStr, list);
	}
	public static String changeType(String string){//数字加逗号
		int i,num,len,count=0,count1=0;;
		String integer,decimal="",temp = "",minus="";
		num=string.indexOf(".");
		if(num==-1){
			integer=string;
		}
		else {
			integer=string.substring(0, num);
			decimal=string.substring(num);
		}
		if (integer.indexOf("-")!=-1) {
			integer=integer.substring(1);
			minus="-";
		}
		len=integer.length();
		for(i=len;i>0;i--){
			if((len-count*3)/3!=0&&count1%3==0&&len!=i){
				temp=String.valueOf(integer.charAt(i-1))+","+temp;
				count1++;
				count++;
			}
			else{
				temp=String.valueOf(integer.charAt(i-1))+temp;
				count1++;
			}
		}
		return minus+temp+decimal;
	}
	public String firstoperating() {
		ArrayList<String> tempList=new ArrayList<String>();
		String v1,v2,operation,result="";
		int num;
		if(findLastElement()){
			num=list.size()-1;
		}else{
			num=list.size();
		}
		for(int i=0;i<num;i++){
			tempList.add(list.get(i));
		}
		if(tempList.size()>=3){
			while(tempList.size()>=3){
				int First_priority=findoperation(tempList,FirstOperators);
				int Second_priority=findoperation(tempList,SecondOperators);
				if(First_priority!=-1){
					v1=tempList.get(First_priority-1);
					Log.d("参数1", v1);
					v2=tempList.get(First_priority+1);
					Log.d("参数2", v2);
					operation=tempList.get(First_priority);
					Log.d("运算符", operation);
					result=operating(v1,v2,operation);
					Log.d("结果", result);
					tempList.remove(First_priority-1);
					tempList.remove(First_priority-1);
					tempList.remove(First_priority-1);
					tempList.add(First_priority-1,result);
					Log.d("临时数组", tempList.toString());
					Log.d("数组", list.toString());
				}else if (Second_priority!=-1) {
					v1=tempList.get(Second_priority-1);
					Log.d("参数1", v1);
					v2=tempList.get(Second_priority+1);
					Log.d("参数2", v2);
					operation=tempList.get(Second_priority);
					Log.d("运算符", operation);
					result=operating(v1,v2,operation);
					Log.d("结果", result);
					tempList.remove(Second_priority-1);
					tempList.remove(Second_priority-1);
					tempList.remove(Second_priority-1);
					tempList.add(Second_priority-1,result);
					Log.d("临时数组", tempList.toString());
					Log.d("数组", list.toString());
				}
			}
		}else{
			result=tempList.get(0);
		}
		return result;
	}
	public int findoperation(ArrayList<String> arrayList,String[] strings) {
		int temp=-1;
		for(int i=0;i<arrayList.size();i++){
			if(operationconfirm(strings,arrayList.get(i))==1){
				temp=i;
				break;
			}
		}
		return temp;
	}
	public int operationconfirm(String[] strings,String string) {
		int temp=-1;
		for(String string1:strings){
			if(string.equals(string1))
			{
				temp=1;
				break;
			}
		}
		return temp;
	}
	public String operating(String string1,String string2,String operation) {
		BigDecimal num1=new BigDecimal(string1);
		BigDecimal num2=new BigDecimal(string2);
		String result;

		if(operation=="+"){
			return num1.add(num2).toString();
		}else if (operation=="-") {
			return num1.subtract(num2).toString();
		}else if(operation=="/"){
			result=num1.divide(num2,15,BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
			return result;
		}else {
			return num1.multiply(num2).toString();
		}
	}
	public void equal() {
		String result;
//		if(list.size()>=3){
			result=firstoperating();
			
//		}else{
//			result=list.get(0);
//		}
		display(result,list);
		numStr="0";
		list.removeAll(list);
	}
	public void display(String string,ArrayList<String> arrayList){
		String temp="";
		TextView textView1=(TextView)findViewById(R.id.display1);
		TextView textView2=(TextView)findViewById(R.id.display2);
		if(ACtemp.equals("0")){
			ac.setText("AC");
		}else {
			ac.setText("C");
		}
		if(string.indexOf("E")!=-1){
			textView1.setText(string);
		}
		else if(string.length()>11){
			MathContext m=new MathContext(6);
			textView1.setText(new BigDecimal(string,m).toEngineeringString());
			Log.d("haha",new BigDecimal(string,m).toEngineeringString());
		}else {
			textView1.setText(changeType(string));
		}
		for(int i=0;i<arrayList.size();i++){
			temp+=arrayList.get(i);
		}
		if(temp.length()>30){
			temp=temp.substring(temp.length()-30, temp.length());
		}
		textView2.setText(temp);
	}
}
