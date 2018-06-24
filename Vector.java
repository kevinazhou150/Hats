package line;

public class Vector {
	public int length;
	public int[] nums;
	public Vector(int num, int length){
		this.length = length;
		nums = new int[length];
		for(int i = length - 1; i > -1; i--){
			nums[i] = num % (int) (Math.pow(10, length-i)) / (int) (Math.pow(10, length -1 -i));
		}
	}
	public boolean equals(Object other){
		if(!(other instanceof Vector)) return false;
		if(this.length != ((Vector) other).length) return false;
		for(int i = 0; i < length; i++){
			if(this.nums[i] != ((Vector) other).nums[i]) return false;
		}
		return true;
	}
	public int hashCode(){
		String ret = "";
		ret += length;
		for(int i = 0; i < length; i++){
			ret += nums[i];
		}
		return Integer.parseInt(ret);
	}
	public String toString(){
		String ret = "[";
		for(int i = 0; i < length; i ++) ret += Integer.toString(nums[i]) + ",";
		return ret + "]";
	}

}
