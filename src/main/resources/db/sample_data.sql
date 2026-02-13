-- Sample data for Fitness IUT application

-- Sample Users
INSERT INTO users (username, email, password, first_name, last_name, age, weight, height, created_at) VALUES
('john_doe', 'john@example.com', 'password123', 'John', 'Doe', 25, 75.5, 180.0, NOW()),
('jane_smith', 'jane@example.com', 'password456', 'Jane', 'Smith', 28, 62.0, 165.0, NOW()),
('mike_wilson', 'mike@example.com', 'password789', 'Mike', 'Wilson', 32, 88.0, 185.0, NOW());

-- Sample Exercises
INSERT INTO exercises (name, description, category, difficulty, duration, calories_burned) VALUES
('Push-ups', 'Exercice de musculation pour le haut du corps', 'Force', 'Débutant', 10, 50),
('Squats', 'Exercice pour les jambes et les fessiers', 'Force', 'Débutant', 15, 80),
('Running', 'Course à pied pour le cardio', 'Cardio', 'Intermédiaire', 30, 300),
('Burpees', 'Exercice complet du corps', 'Cardio', 'Avancé', 20, 200),
('Plank', 'Exercice de gainage pour les abdominaux', 'Force', 'Débutant', 5, 30),
('Jump Rope', 'Saut à la corde pour le cardio', 'Cardio', 'Intermédiaire', 15, 150),
('Pull-ups', 'Tractions pour le dos et les bras', 'Force', 'Avancé', 10, 70),
('Yoga', 'Étirements et relaxation', 'Flexibilité', 'Débutant', 45, 120);

-- Sample Workout Plans
INSERT INTO workout_plans (name, description, user_id, exercise_id, sets, reps, created_at) VALUES
('Programme débutant - Jour 1', 'Programme pour débuter en fitness', 1, 1, 3, 10, NOW()),
('Programme débutant - Jour 1', 'Programme pour débuter en fitness', 1, 2, 3, 15, NOW()),
('Programme cardio', 'Plan d\'entraînement cardio intensif', 2, 3, 1, 30, NOW()),
('Programme cardio', 'Plan d\'entraînement cardio intensif', 2, 6, 3, 100, NOW()),
('Programme force avancé', 'Programme de musculation avancé', 3, 4, 4, 12, NOW()),
('Programme force avancé', 'Programme de musculation avancé', 3, 7, 4, 8, NOW());
