package cl.qs.securitycoreserver.security;


import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    private PasswordUtil() {
        throw new IllegalStateException("Utility class");
    }

    // Define the BCrypt workload to use when generating pass hashes. 10-31 is a valid value.
    private static int workload = 15;

    /**
     * This method can be used to generate a string representing an account pass
     * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
     * hash string of length=60
     * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
     * A workload of 12 is a very reasonable safe default as of 2013.
     * This automatically handles secure 128-bit salt generation and storage within the hash.
     *
     * @param passwordPlainText The account's plaintext pass as provided during account creation,
     *                          or when changing an account's pass.
     * @return String - a string of length 60 that is the bcrypt hashed pass in crypt(3) format.
     */
    public static String getHashPassword(String passwordPlainText) {
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(passwordPlainText, salt);

        return (hashedPassword);
    }

    /**
     * This method can be used to verify a computed hash from a plaintext (e.g. during a login
     * request) with that of a stored hash from a database. The pass hash from the database
     * must be passed as the second variable.
     *
     * @param passwordPlaintext The account's plaintext pass, as provided during a login request
     * @param storedHash        The account's stored pass hash, retrieved from the authorization database
     * @return boolean - true if the pass matches the pass of the stored hash, false otherwise
     */
    public static boolean checkPassword(String passwordPlaintext, String storedHash) {
        boolean passwordVerified = false;

        if (null == storedHash || !storedHash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison");

        passwordVerified = BCrypt.checkpw(passwordPlaintext, storedHash);

        return (passwordVerified);
    }

}
