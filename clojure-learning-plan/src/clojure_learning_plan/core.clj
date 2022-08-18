(ns clojure-learning-plan.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn fizzbuzz 
  [stop]
  (loop [iteration 1]
    (if (= (mod iteration 15) 0) (println "fizzbuzz")
        (if (= (mod iteration 5) 0) (println "buzz")
            (if (= (mod iteration 3) 0) (println "fizz")
                (println iteration))))
    (if (>= iteration stop)
      (println "done")
      (recur (inc iteration)))
    )
  )

(fizzbuzz 10)
  
