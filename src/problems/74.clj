(ns problems.74)

(defn __ [numstr]
  (let [isint #(== % (int %))]
    (clojure.string/join ","
      (filter #(isint (Math/sqrt %))
              (map #(Integer/parseInt %)
                   (.split numstr ","))))))


(assert (= (__ "4,5,6,7,8,9") "4,9"))

(assert (= (__ "15,16,25,36,37") "16,25,36"))
