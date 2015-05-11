(ns problems.148)

; given integers n, a, and b, where a and b are coprime
; find the sum of all integers divisble by a or b less than n

; using mutual inclusion exclusion principle

(defn __ [n a b]
  (letfn [(sumk [k] (quot (*' k (inc k)) 2))
          (sum-n [m] (*' m (sumk (quot (dec n) m))))]
    (-' (+' (sum-n a) (sum-n b)) (sum-n (*' a b)))))


(assert (= 0 (__ 3 17 11)))
(assert (= 23 (__ 10 3 5)))
(assert (= 233168 (__ 1000 3 5)))
(assert (= "2333333316666668" (str (__ 100000000 3 5))))
(assert (= "110389610389889610389610"
  (str (__ (* 10000 10000 10000) 7 11))))

