-- src/main/resources/db/data/initial_data.sql

-- Add the email column to the customer table if it does not exist
ALTER TABLE customer ADD COLUMN IF NOT EXISTS email VARCHAR(100);