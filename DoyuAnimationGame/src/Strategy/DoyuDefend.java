/* Version History  :
 Class   : DoyuDefend
 Author     : Karthick
              Hung Le
              Heli
              Janani
 Description: Actions for all the battle defend cards are described and the running total gets computed
 --===========================================================================================================================
 -- Rev      Date            By                                                        Description                                                 
 --===========================================================================================================================
 -- 1.0    05-Feb-2017     Hung Le, Heli,                     2. Splitted attack and defend strategy into separate class
                           Janani, Karthick 
 -- 3.0    20-Feb-2017 	   Hung Le, Heli,	                  3. Shows the defend strategy and defend operation
                           Janani, Karthick						   
 --===========================================================================================================================
 --============================================================================================================================*/

package Strategy;
import Card.Card;
import Card.DoyuCard;

public class DoyuDefend implements StrategyInterface {

	public DoyuDefend() {

	}

	@Override
	public int performStrategy(Card in, String target) {
		DoyuCard dc = (DoyuCard) in;

		int health = 0, defend_pt = 0, sp_defend_pt = 0, counter_pt = 0;

		int opp_health = 0;
		
		String s[][] = null;
		int tmp_select = 0;

		if (target == "cpu") {
			tmp_select = dc.getSelectedchoice();
			s = dc.getPlayerdeck();

			health = dc.getCpuhealth();
			defend_pt = dc.cpu_defend_pt;
			sp_defend_pt = dc.cpu_SpecialDefend_pt;
			counter_pt = dc.cpu_counter_pt;
			
			opp_health = dc.getPlayerhealth();

		} else if (target == "player") {
			tmp_select = dc.getCPUselectedchoice();
			s = dc.getCpudeck();

			health = dc.getPlayerhealth();
			defend_pt = dc.player_defend_pt;
			sp_defend_pt = dc.player_SpecialDefend_pt;
			counter_pt = dc.player_counter_pt;
			
			opp_health = dc.getCpuhealth();
			
		}

		if (s[tmp_select - 1][0] == "Damage") {
			health += (defend_pt + sp_defend_pt);
			opp_health -= counter_pt;
			
			System.out.println("Defending the opponent's attack");
		}
		
		if (target == "cpu") {
			dc.setCpuhealth(health);
			dc.setPlayerhealth(opp_health);
			
		} else if (target == "player") {
			dc.setPlayerhealth(health);
			dc.setCpuhealth(opp_health);
		}

		dc.setCpuhealth(dc.getCpuhealth() + dc.cpu_regeneration_pt - dc.cpu_poison_pt);
		dc.setPlayerhealth(dc.getPlayerhealth() + dc.player_regeneration_pt - dc.player_poison_pt);
		
		System.out.println("Player-Current Health:--> " + dc.getPlayerhealth() + "  CPU-Current Health:--> " + dc.getCpuhealth());
		if (dc.getCpuhealth() <= 0) {
			System.out.println("CONGRATS..GAME OVER...YOU WON!!!");
			return 1;
		}

		if (dc.getPlayerhealth() <= 0) {
			System.out.println("SORRY..GAME OVER...YOU LOST!!!");
			return 1;
		}
		
		return 0;
	}
}
