package ricm3.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import ricm3.parser.Ast;
import ricm3.parser.Ast.Expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyAutomate {
	
	MyState init;
	List<MyState> etats;
	
	public MyAutomate() {
		etats=new LinkedList<MyState>();
	}

	public static class MyState extends MyAutomate{
		List<MyTransition> trans;
		String name;
		
		public MyState(MyAutomate auto) {
			trans=new LinkedList<MyTransition>();
		}
	}
	
	public static class MyTransition extends MyAutomate{
		MyState cible;
		MyCondition cond;
		MyAction action;
	}
	
	public class MyCondition extends MyAutomate{
		MyExpression expr;
		
		public boolean eval() {
			return expr.eval();
		}
	}
	
	public class MyAction extends MyAutomate{
		
		public boolean exec() {
			
		}
	}
	
	public class MyExpression extends MyAutomate{
		
		public boolean eval() {
			return false;
		}
	}
	
	public class MyUnaryOp extends MyExpression{
		
		String operator;
		MyExpression operand;
		
		@Override
		public boolean eval() {
			//Le seul operateur unaire est not
			return !operand.eval();
		}
	}
	
	public class MyBinaryOp extends MyExpression{
		
		String operator;
		MyExpression left_operand;
		MyExpression right_operand;
		
		@Override
		public boolean eval() {
			switch(operator) {
				case "&":
					return left_operand.eval() && right_operand.eval();
				default: //ou
					return left_operand.eval() || right_operand.eval();
			}
		}
	}
	
	public class MyFunCall extends MyExpression{
		
		String value;
		
		public boolean eval() {
			
			//Decoupage de la string
			String[] splitted1 = value.split("(");
			String methodName = splitted1[0];
			String[] splitted2 = splitted1[1].split(",");
			String param1 = splitted2[0];
			String[] splitted3 = splitted2[1].split(")");
			String param2 = splitted3[0];
			Object obj;
	        Method method;
			
			switch (methodName) {
				//Fonctions sans arg
				case "True":
				case "GotPower":
				case "GotStuff":
					method = obj.getClass().getMethod(methodName);
					return (boolean) method.invoke(obj);
	
				//Fonctions avec 2 args
				case "Key":
				case "MyDir":
					method = obj.getClass().getMethod(methodName,param1.getClass());
					return (boolean) method.invoke(obj, param1);
				case  "Cell":
				case "Closest":
					method = obj.getClass().getMethod(methodName, param1.getClass(), param2.getClass());
					return (boolean) method.invoke(obj, param1, param2);
			}

		}
	}
		
}