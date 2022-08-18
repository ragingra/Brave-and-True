(ns clojure-learning-plan.chapter4
  (:gen-class))

(defn -main
  "Chapter4 doc string!"
  [& args]
  (println "Hello, World!"))

(map inc [1, 2, 3])

(map str ["a", "b", "c"] ["1", "2", "3"])

(def sum #(reduce + %))

(sum [1, 2, 3])

(def avg #(/ (sum %) (count %)))

(avg [1, 2, 3])

(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [1, 2 ,3])

(def identities
  [{:alias "Batmon" :real "Bruce Wayne"}
   {:alias "Spoderman" :real "Peter Parker"}
   {:alias "Santa" :real "Your Mum"}
   {:alias "Easter Bunny" :real "Your Dad"}])

(map :real identities)

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 31 :min 10})

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})

(def listofnums [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])

(take 3 listofnums)
(drop 3 listofnums)

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
(drop-while #(< (:month %) 3) food-journal)
(take-while #(< (:month %) 4) (drop-while #(< (:month %) 2) food-journal))

(filter #(< (:human %) 5) food-journal)

(some #(> (:critter %) 5) food-journal)
(some #(> (:critter %) 3) food-journal)

(some #(and (> (:critter %) 3) %) food-journal)

(sort [3 1 2])

(sort-by count ["aaa" "c" "bb"])

(concat [1 2] [3 4])

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true :name "McMackson"}
   2 {:makes-blood-puns? true, :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true, :has-pulse? true :name "Micky Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(identify-vampire [1 2])

(time (def mapped-details (map vampire-related-details (range 0 1000000))))
(time (first mapped-details))
(time (identify-vampire (range 0 1000000)))

(concat (take 8 (repeat "na")) ["Batman"])

(take 3 (repeatedly (fn [] (rand-int 10))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

(cons 0 '(2 4 6))

(empty? [])
(empty? ["nope"])

(map identity {:sunlight-reaction "Glitter!"})
(into {} (map identity {:sunlight-reaction "Glitter!"}))

(map identity [:garlic :sesame-oil :fried-eggs])
(into [] (map identity [:garlic :sesame-oil :fried-eggs]))

(map identity [:garlic-clove :garlic-clove])
(into #{} (map identity [:garlic-clove :garlic-clove]))

(into {:favourite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])
(into ["cherry"] '("pine" "spruce"))

(into {:favourite-animal "kitty"} {:least-favourite-smell "dog" :relationship-with-teenager "creepy"})

(conj [0] [1])
(into [0] [1])
(conj [0] 1)
(conj [0] 1 2 3 4)
(conj {:time "midnight"} [:place "ye olde cemetarium"])

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [0] 1 2 3 4)

(max 1 2 3)
(apply max [1 2 3])

(defn my-into
  [target additions]
  (apply conj target additions))

(my-into [0] [1 2 3])

(def add10 (partial + 10))
(add10 3)
(add10 5)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))

(add-missing-elements "unobtainium" "adamantium")

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))

(def add20 (my-partial + 20))
(add20 3)

(fn [& more-args]
  (apply + (into [20] more-args)))

(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))
(def emergency (partial lousy-logger :emergency))

(warn "amber light ahead")
(emergency "red light ahead")

(defn identify-humans
  [social-security-numbers]
  (filter #(not (vampire? %))
          (map vampire-related-details social-security-numbers)))

(def not-vampire? (complement vampire?))
(defn identify-humans
  [social-security-numbers]
  (filter not-vampire?
          (map vampire-related-details social-security-numbers)))

(identify-humans [1 2 3])

(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))

(def my-pos? (complement neg?))
(my-pos? 1)

(my-pos? -1)


;; FWPD
(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(convert :glitter-index "3")

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(parse (slurp filename))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullan\" : glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(first (mapify (parse (slurp filename))))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def glitter-output (glitter-filter 3 (mapify (parse (slurp filename)))))

;; Exercise 1
(map :name glitter-output)

;; Exercise 2
(def suspects (mapify (parse (slurp filename))))

(defn append
  [suspectList newSuspect]
  (conj suspectList newSuspect))

(def newSuspect {:name "Bob Ross" :glitter-index 9001})

(def updatedSuspectList (append suspects newSuspect))
(println updatedSuspectList)

;; Exercise 3
(defn validate
  [keywords record]
  (every? record keywords))

(validate [:test] newSuspect)
(validate [:name :glitter-index] newSuspect)

;; Exercise 4
(defn stringify
  [suspectMap]
  (clojure.string/join "\n" (map #(clojure.string/join "," (map % [:name :glitter-index])) suspectMap)))

(def outputFilename "updatedSuspects.csv")

(spit outputFilename (stringify updatedSuspectList))

