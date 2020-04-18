import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyTest {

    @Test
    public void should_pass(){
        assertThat(5).isEqualTo(2+3);
    }

    @Test
    public void should_fail(){
        assertThat(5).isEqualTo(0);
    }
}
