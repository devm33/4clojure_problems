(ns problems.137)

(defn __ [n b]
  (let [q (quot n b) r (rem n b)]
    (if (zero? q) [r]
      (concat (__ q b) [r]))))

(__ 312341234 312341234)
