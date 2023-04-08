package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Company;


@SpringBootApplication
public class AssetmanagementNoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetmanagementNoJpaApplication.class, args);
		
//		int[] a={1,5,3,4,8};
//		int[] b={4,5};
//		int[] c= new int[a.length];
//		
//		int[] d= new int[b.length];
//		
//		System.err.println("Inside Assetmanagement No JPA\n");
//		for(int i=0;i<a.length;i++)
//		{
//		  for(int j=0;j<b.length;j++)
//		  {
//		    if(a[i] == b[j])
//		    {
//		    	 d[j] = b[j];
//		    }
//		    else
//		    {
//		    	c[i] = a[i];
//		    }
//		  }
//		} 
//		
//		for(int i=0;i<d.length;i++)
//		{
//			System.out.println("Matching->> "+d[i]);
//		}
//		
//		for(int i=0;i<c.length;i++)
//		{
//			System.err.println("Not Matching->> "+c[i]);
//		}
	
//		List<Integer> ilist = new ArrayList<>();
//		
//		ilist.add(100);
//		ilist.add(200);
//		ilist.add(300);
//		ilist.add(400);
//		System.out.println("\n Without Stream api \n");
		
//		for(int i=0;i<ilist.size();i++)
//		{
//			System.err.println(ilist.get(i));
//		}
//		
//		System.out.println("\n Using Stream API \n");
//		
//		List<Integer>nlist =  ilist.stream().filter(p-> p >=200)//filtering the data
//			.map(p->p)//fetching the number
//			.collect(Collectors.toList());
//		
//		System.err.println(nlist);
//	
//		StringTokenizer atk = new StringTokenizer("The Avengers");
//		
//		StringTokenizer atkq =  new StringTokenizer("The Guardians Of the Galaxy", "Of");
//		
//		while (atkq.hasMoreElements()) {
//
//			System.err.println(atkq.nextToken());
//		}
//		 
	 
//		System.out.println("\n Converting to MAP \n");
//		
//		Map<Integer, Integer> map = ilist.stream().collect(Collectors.toMap(p ->p,p->p));
//		 
//		System.err.println(map);
//	Properties prod = System.getProperties();
//	
//	Set<Object> set = prod.entrySet();
		
//	Properties p=System.getProperties();  
//	Set set=p.entrySet();  
//  
//	Iterator itr = set.iterator();
//	
//	while(itr.hasNext())
//	{
//		Map.Entry entry = (Map.Entry) itr.next();
//		System.err.println(entry.getKey()+" ->> "+entry.getValue() );
//	}
//		 List<Company> comp = new ArrayList<Company>();  
//	        //Adding Products  
//		  
//		 comp.add(new Company((long) 1, "TCS"));
//		 comp.add(new Company((long) 2, "BlackRock"));
//		 comp.add(new Company((long) 3, "IBM"));
//		  
//		  
//		 Collections.sort(comp , new NameComparator());
//		  
//		 List<String> cname = comp.stream().filter(cm -> cm.getComp_id()>=2).map(cm->cm.getComp_name()).collect(Collectors.toList());
//		  
//		 Company compnm= comp.stream().min((comp1,comp2)->comp1.getComp_id()>comp2.getComp_id() ? 1 :1).get();
//		 
//		 System.out.println(compnm.getComp_name());
//	        productsList.add(new Company(1,"HP Laptop",25000f));  
//	        productsList.add(new Company(2,"Dell Laptop",30000f));  
//	        productsList.add(new Company(3,"Lenevo Laptop",28000f));  
//	        productsList.add(new Company(4,"Sony Laptop",28000f));  
//	        productsList.add(new Company(5,"Apple Laptop"));  
	        
	}
}

