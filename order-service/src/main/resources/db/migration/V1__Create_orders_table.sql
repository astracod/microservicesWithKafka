DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE tablename = 'orders') THEN
        CREATE TABLE orders (
            id UUID PRIMARY KEY,
            user_id UUID NOT NULL,
            product_id UUID NOT NULL,
            quantity INT NOT NULL,
            status VARCHAR(20) NOT NULL,
            created_at TIMESTAMP DEFAULT NOW()
        );
        RAISE NOTICE 'Table created successfully';
    ELSE
        RAISE NOTICE 'Table already exists';
    END IF;
END $$;