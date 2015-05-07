(ns problems.104)

(defn __ [n]
  (apply
    str
    (map #(nth %2 %1)
         (reverse (map #(mod (quot n %) 10) (take 4 (iterate #(* 10 %) 1))))
         [(take 10 (iterate #(str % "M") ""))
          ["" "C" "CC" "CCC" "CD" "D" "DC" "DCC" "DCCC" "CM"]
          ["" "X" "XX" "XXX" "XL" "L" "LX" "LXX" "LXXX" "XC"]
          ["" "I" "II" "III" "IV" "V" "VI" "VII" "VIII" "IX"]
          ])))


(assert (= "I" (__ 1)))

(assert (= "XXX" (__ 30)))

(assert (= "IV" (__ 4)))

(assert (= "CXL" (__ 140)))

(assert (= "DCCCXXVII" (__ 827)))

(assert (= "MMMCMXCIX" (__ 3999)))

(assert (= "XLVIII" (__ 48)))
