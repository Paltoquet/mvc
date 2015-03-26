//package modèle;
//
//import java.util.ArrayList;
//import interfac.Observer;
//import interfac.Observable;
//
//
//import Ressource.Info;
//
//public class Manager implements Observable {
//	ArrayList<Info> list;
//	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
//	public Manager() {
//		list = new ArrayList<Info>();
//	}
//
//	public void ajfilm(Info nouv) {
//		System.out.println(list.size());
//		list.add(nouv);
//		for(Info i : list){
//			System.out.println(i.getname());
//		}
//		notifyObserver();
//	}
//
//	public void suppfilm(String str) {
//		int i;
//		for (i = 0; i < list.size(); i++) {
//			if (list.get(i).getname().equals(str)) {
//				list.remove(list.get(i));
//			}
//
//		}
//	}
//	@Override
//	public void notifyObserver() {
//		for(Observer obs : listObserver)
//		      obs.update(list);
//	}
//	public void addObserver(Observer obs) {
//	    this.listObserver.add(obs);
//	  }
//
//	@Override
//	public void removeObserver() {
//		// TODO Auto-generated method stub
//
//	}
//
//
//}
//
