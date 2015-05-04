(ns problems.75)

(defn psieve [n]
  ; find all primes <= n
  (loop [s (set (range 2 (inc n))) p []]
    (if (empty? s)
      p
      (let [p0 (apply min s)]
        (recur
          (apply disj s (range p0 (inc n) p0))
          (conj p p0))))))

(defn pfactors [n]
  ; find the prime factorization of n
  ; return a vector of prime factors in lowest form
  (if (< n 2)
    []
    (loop [primes (psieve (inc (int (Math/sqrt n))))
           factors []
           cur n]
      (let [p (first primes)]
        (if (or (empty? primes) (> (* p p) cur))
          (if (> cur 1)
            (conj factors cur)
            factors)
          (let [p (first primes)]
            (recur
              (rest primes)
              (if (= (mod n p) 0)
                (conj factors p)
                factors)
              (loop [c cur]
                (if (not= (mod c p) 0)
                  c
                  (recur (/ c p)))))))))))

(defn totient [n]
  (if (= n 1)
    1
    (apply * n (map #(- 1 (/ 1 %)) (pfactors n)))))

; crammed together for 4clojure
(def __
  (fn [n]
    (if (= n 1)
      1
      (let [psieve (fn [n]
                     (loop [s (set (range 2 (inc n))) p []]
                       (if (empty? s)
                         p
                         (let [p0 (apply min s)]
                           (recur
                             (apply disj s (range p0 (inc n) p0))
                             (conj p p0)))))) ]
        (apply
          *
          n
          (map
            #(- 1 (/ 1 %))
            (loop [primes (psieve (inc (int (Math/sqrt n))))
                   factors []
                   cur n]
              (let [p (first primes)]
                (if (or (empty? primes) (> (* p p) cur))
                  (if (> cur 1)
                    (conj factors cur)
                    factors)
                  (let [p (first primes)]
                    (recur
                      (rest primes)
                      (if (= (mod n p) 0)
                        (conj factors p)
                        factors)
                      (loop [c cur]
                        (if (not= (mod c p) 0)
                          c
                          (recur (/ c p))))))))))))))
  )

(assert (= (__ 1) 1))

(assert (= (__ 10) (count '(1 3 7 9)) 4))

(assert (= (__ 40) 16))

(assert (= (__ 99) 60))

