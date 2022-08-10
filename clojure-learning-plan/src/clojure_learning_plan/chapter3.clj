(ns clojure-learning-plan.chapter3
  (:gen-class))

(defn -main
  "Chapter3 doc string!"
  [& args]
  (println "Hello, World!"))

(defn useStr
  "Takes in string name and returns message"
  [name]
  (format "Hey %s, bet youre enjoying learning clojure" name)
  )

(useStr "Neil")

(defn useVector
  "Takes in vector of pets names"
  [petList]
  (if (empty? petList) 
    (println "You need some pets...") 
    (if (> (count petList) 2)
      (println (format "Bet its a mad house... hope %s get on together" (apply str (interpose ", " petList))))
      (println (format "Awesome! Get the treats out for %s" (apply str (interpose " and " petList))))
      )
  ))

(useVector ["Clint" "Parsley" "Bobby"])

(defn fib
  "fib - not very efficent recursive. need to add memo"
  [num]
  (if  (< num 2)
    1 
    (+ (fib (+ num -1)) (fib (+ num -2)))))

(fib 10)

(defn useHashSet
  "Remove dupes from list"
  [duplist]
  (seq (set duplist)))

(useHashSet (list 1, 1, 2, 2))

(defn add100
  "Return number plus 100"
  [num]
  (+ 100 num))

(add100 10)

(defn dec-maker
  "Creates a custom decrementor"
  [num]
  #(- % num))

(def dec9 (dec-maker 9))

(dec9 10)

(defn mapset
  [num_list]
  (set (map inc num_list)))

(mapset [1 1 2 2])

(def alien-body-parts [{:name "head" :size 3}
                       {:name "multi-eye" :size 1}
                       {:name "multi-ear" :size 1}
                       {:name "multi-foot" :size 2}
                       {:name "abdomen" :size 6}
                       {:name "back" :size 10}
                       {:name "chest" :size 10}])

;; handling datatypes a little funny, ending up with sets not on same levels. had to use apply concat to fix
(defn generateMulti
  [part] 
  (if (re-find #"^multi-" (:name part))
    (into [] (repeat 5 part))
    [part]))

(defn generateAlien
  [part](apply concat(map generateMulti part))
)

(generateAlien alien-body-parts)

(def general-body-parts [{:name "head" :size 3 :quantity 2}
                       {:name "eye" :size 1 :quantity 1}
                       {:name "ear" :size 1 :quantity 2}
                       {:name "foot" :size 2 :quantity 4}
                       {:name "abdomen" :size 6 :quantity 1}
                       {:name "back" :size 10 :quantity 1}
                       {:name "chest" :size 10 :quantity 1}])
  
(defn generateQuanity
  [part]
  (repeat (:quantity part) {:name (:name part) :size (:size part)}))

(defn generateGeneral
  [part] (apply concat (map generateQuanity part))
)

(generateGeneral general-body-parts)


