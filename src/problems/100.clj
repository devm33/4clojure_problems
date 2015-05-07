(ns problems.100)

(defn lcm
  ([a b] (letfn [(gcd [a b] (if (= b 0) a (recur b (mod a b))))]
           (/ (* a b) (gcd a b))))
  ([a b & more] (reduce lcm (lcm a b) more)))

(def __ lcm)

(assert (== (__ 2 3) 6))

(assert (== (__ 5 3 7) 105))

(assert (== (__ 1/3 2/5) 2))

(assert (== (__ 3/4 1/6) 3/2))

(assert (== (__ 7 5/7 2 3/5) 210))
