-- fitness_iut_dump.sql
-- Database: fitness_iut

DROP DATABASE IF EXISTS fitness_iut;
CREATE DATABASE fitness_iut CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE fitness_iut;

-- Table: routines
CREATE TABLE routines (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    description TEXT,
    creation_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: exercises
CREATE TABLE exercises (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    repetitions INT NOT NULL,
    weight DOUBLE NOT NULL,
    routine_id BIGINT NOT NULL,
    FOREIGN KEY (routine_id) REFERENCES routines(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Sample data
INSERT INTO routines (name, description, creation_date, status) VALUES
('Routine Débutant', 'Programme pour débutants', '2024-01-15', 'active'),
('Force et Puissance', 'Programme axé sur la force', '2024-01-20', 'active'),
('Cardio Intense', 'Programme cardiovasculaire intense', '2024-02-01', 'active'),
('Routine Complète', 'Programme complet corps entier', '2024-02-10', 'inactive');

INSERT INTO exercises (name, repetitions, weight, routine_id) VALUES
('Pompes', 15, 0, 1),
('Squats', 20, 0, 1),
('Abdos', 30, 0, 1),
('Développé couché', 10, 60, 2),
('Squat barre', 8, 100, 2),
('Soulevé de terre', 6, 120, 2),
('Course à pied', 1, 0, 3),
('Burpees', 15, 0, 3),
('Mountain climbers', 30, 0, 3),
('Développé militaire', 12, 40, 4),
('Tractions', 10, 0, 4),
('Dips', 12, 0, 4),
('Fentes', 15, 20, 4);
