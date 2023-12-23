package util;

import entity.Loan;
import entity.Student;
import lombok.Getter;

import java.time.LocalDate;

public class SecurityContext {

        private SecurityContext() {
        }

        @Getter
        private static Student currentUser;
        @Getter
        private static LocalDate todayDate;
        @Getter
        private static Loan thisLoan;

        public static void fillContext(Student baseUser) {
            currentUser = baseUser;
        }
        public static void fillContext(LocalDate today) {
            todayDate = today;
        }
        public static void fillContext(Loan loan) {
            thisLoan = loan;
        }

        public static void logout() {
            currentUser = null;
        }

        public static Integer getCurrentUserId() {
            return currentUser.getId();
        }

    }

