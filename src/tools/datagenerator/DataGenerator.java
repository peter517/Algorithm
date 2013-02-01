package tools.datagenerator;

import java.util.Random;

public class DataGenerator {

	private static Random random = new Random();
	
	public static Integer getRandomInteger(){
		return  integerGenerator.next();
	}
	
	public static Integer getRandomInteger(int topLimit){
		return  integerGenerator.next(topLimit);
	}
	
	public static Integer[] getRandomIntegerArray(int length){
		return  integerGenerator.nextArray(length);
	}
	
	public static Integer[] getRandomIntegerArray(int length, int topLimit){
		return  integerGenerator.nextArray(length, topLimit);
	}
	
	public static Character getRandomCharacter(){
		return  charctorGenerator.next();
	}
	
	public static Character[] getRandomCharacterArray(int length){
		return  charctorGenerator.nextArray(length);
	}
	
	
	private static  Generator<Integer>  integerGenerator = new Generator<Integer>() {
		@Override
		public Integer next() {
			return random.nextInt();
		}
		@Override
		public Integer[] nextArray(int length) {
			Integer[] result = new Integer[length];
			for(int i = 0; i < result.length; i++){
				result[i] = next();
			}
			return result;
		}
		@Override
		public Integer next(int topLimit) {
			return random.nextInt(topLimit);
		}
		@Override
		public Integer[] nextArray(int length, int topLimit){
			Integer[] result = new Integer[length];
			for(int i = 0; i < result.length; i++){
				result[i] = next(topLimit);
			}
			return result;
		}
	};
	
	private static Generator<Float> floatGenerator = new Generator<Float>() {
		@Override
		public Float next() {
			return random.nextFloat();
		}
		@Override
		public Float[] nextArray(int length) {
			Float[] result = new Float[length];
			for(int i = 0; i < result.length; i++){
				result[i] = next();
			}
			return result;
		}
		@Override
		public Float next(int topLimit) {
			return null;
		}
		@Override
		public Float[] nextArray(int length, int topLimit) {
			return null;
		}
	
	};
	
	private static Generator<Boolean> booleanGenerator = new Generator<Boolean>() {
		@Override
		public Boolean next() {
			return random.nextBoolean();
		}
		@Override
		public Boolean[] nextArray(int length) {
			
			Boolean[] result = new Boolean[length];
			for(int i = 0; i < result.length; i++){
				result[i] = next();
			}
			return result;
		}
		@Override
		public Boolean next(int topLimit) {
			return null;
		}
		@Override
		public Boolean[] nextArray(int length, int topLimit) {
			return null;
		}
	};
	
	private static Generator<Character> charctorGenerator = new Generator<Character>(){

		private String baseData = "abcdefghijklmnopqrstuvwxyz";
		@Override
		public Character next() {
			return baseData.charAt(getRandomInteger(baseData.length()));
		}

		@Override
		public Character next(int topLimit) {
			return null;
		}

		@Override
		public Character[] nextArray(int length) {
			Character[] result = new Character[length];
			for(int i = 0; i < result.length; i++){
				result[i] = next();
			}
			return result;
		}

		@Override
		public Character[] nextArray(int length, int topLimit) {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	 public static void main(String[] args) {
//		 Integer[] data = DataGenerator.getRandomIntegerArray(10, 10);
		 Character[] data = DataGenerator.getRandomCharacterArray(10);
		 
		 StringBuffer str = new StringBuffer();
		 for(int i = 0; i < data.length; i++){
			 str.append(data[i]);
		 }
		 System.out.println(str);
	 }
}
