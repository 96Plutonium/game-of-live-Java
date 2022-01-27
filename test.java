import java.util.Random; 

class Map {
	int width = 0;
	int height = 0;
	boolean map[][] = new boolean[height][width];
	Map (int width, int height)
	{
	        this.width = width;
		this.height = height;
		this.map = new boolean[height][width];
		for (int h = 0; h< height-1; h++)
		{
			for (int w = 0; w < width-1; w++)
				{
				this.map[h][w] = GetRandom(); 
				}
		}
	}
	private boolean GetRandom()
	{
		return Math.random() < 0.5;
	}
	public boolean isAlive(int x, int y){
		if (x < 0 ||  y < 0 || x >= this.height || y >= this.width)
		{return false;}
		return this.map[x][y];
	}
	public void Draw(){
		for (int i = 0; i < this.height; i++)
		{
			for ( int b = 0; b < this.width; b++)
			{
				char pixel;
				if (this.map[i][b] == false)
				{
					pixel = ' ';
				}
				else {
					pixel = '';
				}
				System.out.print(pixel);
			}
			System.out.print("\n");
		}
	}
	public int GetNeighbours(int x, int y){
		int ret = 0;
		//check X*X	
		if ((x >= 1) & (this.isAlive(x-1, y)))
		{
			ret++;
		}
		if ((this.isAlive(x + 1, y)) & (x != this.width))
		{
			ret++;
		}
			
		//Check XXX
		//	 *
		//	XXX
		if (y != 0)
		{
		for (int i = 0; i < 3; i++)
		{
			if (this.isAlive(x - 1 + i, y - 1))
			{
				ret++;
			}
		}
		}
		if ( y != this.height ){
		for (int i = 0; i < 3; i++)
		{
			if (this.isAlive(x - 1 + i, y + 1))
			{
				ret++;
			}
		}
		}

		return ret;
	}
	public void move()
	{
	boolean map2[][] = new boolean[this.height][this.width];
	for(int h = 0; h < this.height; h++)
	{
		for (int w = 0; w < this.width; w++)
		{
			int nr = 0;
			nr = this.GetNeighbours(h, w);
			if (nr < 2) { map2[h][w] = false; }
			else if (nr > 3) { map2[h][w] = false; }
			else if (nr == 3) {map2[h][w] = true; }
			else if (nr == 2) {map2[h][w] = this.map[h][w];}
		}
	}
	this.map = map2;
	}
}	

class test {
	static void clear_screen(int height)
	{
	for (int h = 0; h < height; h++)
	{
		System.out.println("\n");
	}
	}
	public static void main(String[] args)
	{
	//default linux terminal(you fodbid us to use curses) 
	//is 80x25 so
	int width = 80;
	int height = 25;
	Map map = new Map(width, height);
	for (int i = 0; i < 255; i++)
	{
		clear_screen(height);
		map.Draw();
		try {Thread.sleep(250);}
		catch (InterruptedException ex)
		{Thread.currentThread().interrupt();}
		map.move();
	}
}
}
