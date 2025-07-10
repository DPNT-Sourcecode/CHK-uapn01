package io.accelerate.solutions.CHK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    private CheckoutSolution chk;

    @BeforeEach
    public void setUp() {
        chk = new CheckoutSolution();
    }

    @Test
    public void checkout_test() {

        assertThat(chk.checkout("AA"), equalTo(100));
        assertThat(chk.checkout("AAAA"), equalTo(180));
        assertThat(chk.checkout("AAAAA"), equalTo(200));
        assertThat(chk.checkout("AAAAAA"), equalTo(250));
        assertThat(chk.checkout("AAAAAAA"), equalTo(300));
        assertThat(chk.checkout("AABB"), equalTo(145));
        assertThat(chk.checkout("AABEE"), equalTo(180));
        assertThat(chk.checkout("AABBEEEE"), equalTo(260));
        assertThat(chk.checkout("AAA"), equalTo(130));
        assertThat(chk.checkout("DDD"), equalTo(45));
        assertThat(chk.checkout("X"), equalTo(-1));
    }
}




