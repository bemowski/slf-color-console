package net.jmatrix.console.log;

public enum Level {
   ALL(100), TRACE (20), DEBUG(10), INFO(8), WARN(6), ERROR(4);

   int ilevel=0;
   
   Level(int i) {
      ilevel=i;
   }
   
   public int getILevel() {return ilevel;}
}
