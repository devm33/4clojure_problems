(ns problems.113)

(defn __ [& args]
  (when args
    (reify clojure.lang.Seqable
      (seq [this] (distinct args))
      (toString [this] (clojure.string/join ", " (sort args))))))




(assert (= "1, 2, 3" (str (__ 2 1 3))))

(assert (= '(2 1 3) (seq (__ 2 1 3))))

(assert (= '(2 1 3) (seq (__ 2 1 3 3 1 2))))

(assert (= '(1) (seq (apply __ (repeat 5 1)))))

(assert (= "1, 1, 1, 1, 1" (str (apply __ (repeat 5 1)))))

(assert (and (= nil (seq (__)))
     (=  "" (str (__)))))
