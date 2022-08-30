(ns clojure-learning-plan.chapter6
  (:gen-class))

(defn -main
  "Chapter6 doc string!"
  [& args]
  (println "Hello, World!"))

inc

'inc

(map inc [1 2])

'(map ic [1 2])

(def great-books ["East of Eden" "The Glass Bead Game"])
great-books

(ns-interns *ns*)

(get (ns-interns *ns*) 'great-books)

(deref #'user/great-books)

great-books

(def great-books ["The Power of Bees" "Journey to Upstairs"])
great-books

(create-ns 'cheese.taxonomy)
(ns-name (create-ns 'cheese.taxonomy))

(in-ns 'cheese.analysis)

(in-ns 'cheese.taxonomy)
(def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
(def bries ["Wisconsis" "Somerset" "Brie de Meaux" "Brie de Melun"])
(in-ns 'cheese.analysis)
(clojure.core/refer 'cheese.taxonomy)
bries

cheddars

(clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'bries)
(clojure.core/get (clojure.core/ns-map clojure.core/*ns*) 'cheddars)

(clojure.core/refer 'cheese.taxonomy :only ['bries])
bries
cheddars
(clojure.core/refer 'cheese.taxonomy :exclude ['bries])
bries
cheddars

(clojure.core/refer 'cheese.taxonomy :rename {'bries 'yummy-bries})
yummy-bries

(in-ns 'cheese.analysis)

(defn- private-function
  "Just an example fucntuon that does nothing"
  [])

(in-ns 'cheese.taxonomy)
(clojure.core/refer-clojure)
(cheese.analysis/private-function)
(refer 'cheese.analysis :only ['private-function])

(clojure.core/alias 'taxonomy 'cheese.taxonomy)
taxonomy/bries








