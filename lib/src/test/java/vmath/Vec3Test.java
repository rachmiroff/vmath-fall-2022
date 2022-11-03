package vmath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Vec3Test {
    @Test
    void creates_new_zero_vector() {
        var v = new Vec3();
        assertEquals(v.x(), 0.0f);
        assertEquals(v.y(), 0.0f);
        assertEquals(v.z(), 0.0f);
    }

    @Test
    void creates_new_vector_with_args() {
        var v = new Vec3(1.0f, 2.0f, 3.0f);
        assertEquals(v.x(), 1.0f);
        assertEquals(v.y(), 2.0f);
        assertEquals(v.z(), 3.0f);
    }

    @Test
    void fails_to_create_new_nan_vector() {
        assertThrows(ArithmeticException.class, () -> new Vec3(Float.NaN, Float.NaN, Float.NaN));
    }

    @Test
    void calculates_zero_vector_magnitude() {
        var v = new Vec3(0.0f, 0.0f, 0.0f);
        assertEquals(v.mag(), 0.0f);
    }

    @Test
    void calculates_nonzero_vector_mag() {
        var v1 = new Vec3(1.0f, 0.0f, 0.0f);
        assertEquals(v1.mag(), 1.0f);
        var v2 = new Vec3(0.0f, 1.0f, 0.0f);
        assertEquals(v2.mag(), 1.0f);
        var v3 = new Vec3(0.0f, 0.0f, 1.0f);
        assertEquals(v3.mag(), 1.0f);
        var v4 = new Vec3(-1.0f, 0.0f, 0.0f);
        assertEquals(v4.mag(), 1.0f);
        var v5 = new Vec3(0.0f, -1.0f, 0.0f);
        assertEquals(v5.mag(), 1.0f);
        var v6 = new Vec3(0.0f, 0.0f, -1.0f);
        assertEquals(v6.mag(), 1.0f);
        var v7 = new Vec3(1.0f, 1.0f, 1.0f);
        assertEquals(v7.mag(), 1.7320508075688772f);
    }

    @Test
    void calculates_zero_vector_squared_magnitude() {
        var v = new Vec3(0.0f, 0.0f, 0.0f);
        assertEquals(v.sqrdMag(), 0.0f);
    }

    @Test
    void calculates_nonzero_vector_squared_mag() {
        var v = new Vec3(1.0f, 1.0f, 1.0f);
        assertEquals(v.sqrdMag(), 3.0f);
    }

    @Test
    void normalizes_nonzero_vector() {
        var v = new Vec3(0.0f, 10.0f, 0.0f).norm();
        assertEquals(v.x(), 0.0f);
        assertEquals(v.y(), 1.0f);
        assertEquals(v.z(), 0.0f);
    }

    @Test
    void fails_to_normalize_zero_vector() {
        assertThrows(ArithmeticException.class, () -> {
            new Vec3(0.0f, 0.0f, 0.0f).norm();
        });
    }

    @Test
    void adds_two_vectors() {
        var v1 = new Vec3(1.0f, 2.0f, 3.0f);
        var v2 = new Vec3(2.0f, 3.0f, 4.0f);
        var sum1 = v1.add(v2);
        assertEquals(sum1.x(), 3.0f);
        assertEquals(sum1.y(), 5.0f);
        assertEquals(sum1.z(), 7.0f);

        var v3 = new Vec3(-1.0f, 1.0f, -1.0f);
        var v4 = new Vec3(1.0f, -1.0f, 1.0f);
        var sum2 = v3.add(v4);
        assertEquals(sum2.x(), 0.0f);
        assertEquals(sum2.y(), 0.0f);
        assertEquals(sum2.z(), 0.0f);
    }

    @Test
    void subtracts_two_vectors() {
        var v1 = new Vec3(1.0f, 2.0f, 3.0f);
        var v2 = new Vec3(2.0f, 3.0f, 4.0f);
        var diff1 = v1.sub(v2);
        assertEquals(diff1.x(), -1.0f);
        assertEquals(diff1.y(), -1.0f);
        assertEquals(diff1.z(), -1.0f);

        var v3 = new Vec3(-1.0f, 1.0f, -1.0f);
        var v4 = new Vec3(1.0f, -1.0f, 1.0f);
        var diff2 = v3.sub(v4);
        assertEquals(diff2.x(), -2.0f);
        assertEquals(diff2.y(), 2.0f);
        assertEquals(diff2.z(), -2.0f);
    }

    @Test
    void multiplies_vector_by_scalar() {
        var v1 = new Vec3(1.0f, 2.0f, 3.0f);
        var prod1 = v1.mul(2.0f);
        assertEquals(prod1.x(), 2.0f);
        assertEquals(prod1.y(), 4.0f);
        assertEquals(prod1.z(), 6.0f);

        var v3 = new Vec3(-1.0f, 1.0f, -1.0f);
        var prod2 = v3.mul(0.0f);
        assertEquals(prod2.x(), -0.0);
        assertEquals(prod2.y(), 0.0f);
        assertEquals(prod2.z(), -0.0f);
    }

    @Test
    void calculates_dot_product_for_vectors() {
        var n1 = new Vec3(1.0f, 2.0f, 3.0f);
        var l1 = new Vec3(2.0f, 3.0f, 4.0f);
        var dot1 = n1.dot(l1);
        assertEquals(dot1, 20.0f);

        var n2 = new Vec3(-1.0f, 1.0f, -1.0f);
        var l2 = new Vec3(1.0f, -1.0f, 1.0f);
        var dot2 = n2.dot(l2);
        assertEquals(dot2, -3.0f);
    }

    @Test
    void calculates_cross_product_for_vectors() {
        var v1 = new Vec3(1.0f, 2.0f, 3.0f);
        var v2 = new Vec3(2.0f, 3.0f, 4.0f);
        Vec3 cross = v1.cross(v2);
        assertEquals(cross.x(), -1.0f);
        assertEquals(cross.y(), 2.0f);
        assertEquals(cross.z(), -1.0f);
    }

    @Test
    void linearly_interpolates_two_vectors() {
        var v1 = new Vec3(1.0f, 2.0f, 3.0f);
        var v2 = new Vec3(2.0f, 3.0f, 4.0f);
        float alpha = 0.5f;
        Vec3 lerped = v1.lerp(v2, alpha);
        assertEquals(lerped.x(), 1.5f);
        assertEquals(lerped.y(), 2.5f);
        assertEquals(lerped.z(), 3.5f);
    }

    @Test
    void converts_zero_vector_to_string() {
        var v1 = new Vec3();
        assertEquals(v1.toString(), "(0.0, 0.0, 0.0)");
    }

    @Test
    void converts_int_param_vector_to_string() {
        var v2 = new Vec3(1, 2, 3);
        assertEquals(v2.toString(), "(1.0, 2.0, 3.0)");
    }

    @Test
    void converts_real_param_vector_to_string() {
        var v3 = new Vec3(0.5f, 0.25f, 0.125f);
        assertEquals(v3.toString(), "(0.5, 0.25, 0.125)");
    }
}
