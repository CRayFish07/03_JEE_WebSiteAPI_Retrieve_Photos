package com.joe.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestGoogleGson {

	public static void main(String[] args) {
		TestGoogleGson tgg = new TestGoogleGson();
		tgg.objects2JsonString();
		tgg.jsonString2Object();
	}

	private void objects2JsonString() {
		Gson gson = new Gson();
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			Person p = new Person();
			p.setName("name" + i);
			p.setAge(i * 5);
			persons.add(p);
		}
		String str = gson.toJson(persons);

		// [
		// {"name":"name0","age":0},
		// {"name":"name1","age":5},
		// {"name":"name2","age":10},
		// {"name":"name3","age":15},
		// {"name":"name4","age":20}
		// ]
		System.out.println(str);
	}

	private void jsonString2Object() {
		//Person person = gson.fromJson(str, Person.class);
		
		Gson gson = new Gson();
		String str = "[{'name':'name0','age':0},{'name':'name1','age':5},{'name':'name2','age':10}]";
		List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>() {}.getType());
		for (int i = 0; i < ps.size(); i++) {
			Person p = ps.get(i);
			//name0:0
			//name1:5
			//name2:10
			System.out.println(p.toString());
		}
		
		str = "{'name':'name0','age':0}";
		Person person = gson.fromJson(str, Person.class);
		//name0
		System.out.println(person.getName());
	}
}
