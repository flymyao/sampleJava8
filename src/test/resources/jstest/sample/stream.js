var list2 = new java.util.ArrayList();
	list2.add("d2");
	list2.add("a2");
	list2.add("b1");
	list2.add("a1");
	list2.add("b3");
	list2.add("c");
	list2.add("b2");
	list2.add("d1");
	
	list2.stream().filter(function(el) {
		return el.endsWith("1");
	}).sorted().forEach(function(el) {
		print(el);
	}); 