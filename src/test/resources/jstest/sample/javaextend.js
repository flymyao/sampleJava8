var Predicate = Java.type('java.util.function.Predicate');
var PredicateFirst = Java.extend(Predicate, {
    test: function(val) {
        return val > 0 ? true : false;
    }
});
print(new PredicateFirst().test(1));

var Function = Java.type('java.util.function.Function');
print(new Function(){
	apply: function(val) {
        return val === "love" ? "love" : "hate";
    }
}.apply("love"));