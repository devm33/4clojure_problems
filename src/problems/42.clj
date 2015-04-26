(ns problems.42)

(defn fact [n]
  (apply * (range 1 (inc n))))

(assert (= (fact 5) 120))

(assert (= (fact 4) 24))

(assert (= (fact 8) 40320))