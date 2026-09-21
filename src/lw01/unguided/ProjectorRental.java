package lw01.unguided;

public class ProjectorRental extends Rental {

    ProjectorRental(String id, int days) {
        super(id, days);
    }
    @Override
    public int calculateCharge() {
      int days = getDays();
      int total;
      if(days<=3){
        total = days * 60000;
      }
      else{
        total = 3 * 60000 + (days-3)*45000;
      }
      return (total + 20000) * 2; 
    }
    @Override 
    public String label(){
        return "Projector";
    }
    
}
