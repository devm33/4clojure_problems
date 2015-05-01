(ns problems.65)

; this is messy and i dont like it

(defn inst? [c x] (. c (isInstance x)))

(def __
  (fn [x]
    (let [i? inst?]
      (second
        (first
          (filter
            #(i? (first %) x)
            {
             clojure.lang.LazySeq :list
             clojure.lang.IPersistentList :list
             clojure.lang.IPersistentMap :map
             clojure.lang.PersistentVector :vector
             clojure.lang.IPersistentSet :set
             })))))
  )

;; (println (inst? clojure.lang.IPersistentMap {}))
;; (println (inst? clojure.lang.PersistentVector []))
;; (println (inst? clojure.lang.IPersistentList ()))
;; (println (inst? clojure.lang.LazySeq (range)))
;; (println (inst? clojure.lang.IPersistentSet #{}))

(assert (= :map (__ {:a 1, :b 2})))
(assert (= :list (__ (range (rand-int 20)))))
(assert (= :vector (__ [1 2 3 4 5 6])))
(assert (= :set (__ #{10 (rand-int 5)})))
(assert (= [:map :set :vector :list] (map __ [{} #{} [] ()])))
