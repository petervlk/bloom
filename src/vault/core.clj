(ns vault.core
  (:require
    [cryptohash-clj.api :as crypto]
    [cryptohash-clj.encode :as enc]
    [cryptohash-clj.globals :refer [next-random-bytes!]]))

(def salt (next-random-bytes! 16))

(defn derived-password-key
  "Takes user password and derives a password-key using randomly generated user-key as a salt. "
  [password ^bytes user-key]
  (crypto/hash-with :argon2 password {:salt user-key}))

(comment

  (enc/to-b64-str (enc/from-b64-str "CdzWmxF8giOECEWJsvgxlA"))

  (crypto/hash-with :argon2 "Vlko" {:salt salt})

  ;; => "100$12$argon2id$v13$CdzWmxF8giOECEWJsvgxlA$i1XXBZEI1X2guVu2hc8eebO0lmGIc5uyW1LB6Sfpi3Y$$$"

  ;; => ["100"
  ;;     "12"
  ;;     "argon2id"
  ;;     "v13"
  ;;     "CdzWmxF8giOECEWJsvgxlA"
  ;;     "i1XXBZEI1X2guVu2hc8eebO0lmGIc5uyW1LB6Sfpi3Y"]

  *e)

(defn foo
  "I don't do a whole lot."
  [x]
  (prn x "Hello, World!"))
