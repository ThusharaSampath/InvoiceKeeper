package manager;

import dataSource.DataClass;
import entities.Dealer;

public class dealerManager {
	private static dealerManager instance = new dealerManager();

	private dealerManager() {
	}

	public static dealerManager getInstance() {
		return instance;
	}

	public static Dealer createDealer(String dealerName,long DealerTel,int DealerTime) {
		Dealer dealer = new Dealer();
		dealer.setDealerName(dealerName);
		dealer.setDealerTel(DealerTel);
		dealer.setDealerTime(DealerTime);
	
		return dealer;
	}
	
	public static void saveDealer(String dealerName, long DealerTel, int DealerTime) {
		Dealer dealer = new Dealer();
		dealer = createDealer(dealerName,DealerTel,DealerTime);
		DataClass.saveDealer(dealer);
	
	}

}
