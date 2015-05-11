(ns problems.148)

; given integers n, a, and b, where a and b are coprime
; find the sum of all integers divisble by a or b less than n

; mutual inclusion exclusion principle

(defn __ [n a b]
  (letfn [(sumk [k] (quot (* k (inc k)) 2))
          (sum-n [m] (* m (sumk (quot n m))))]
    (- (+ (sum-n a) (sum-n b)) (sum-n (* a b)))))




(assert (= 0 (__ 3 17 11)))
(__ 10 3 5)
;; (__ 1000 3 5)
