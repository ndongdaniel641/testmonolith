
-- Insertion dans t_student
INSERT INTO t_student (st_name, st_code) VALUES
                                             ('Alice Dupont', 'STU001'),
                                             ('Bruno Kamdem', 'STU002'),
                                             ('Claire Nguema', 'STU003'),
                                             ('David Talla', 'STU004'),
                                             ('Emilie Kouam', 'STU005'),
                                             ('Franck Njoya', 'STU006'),
                                             ('Grace Mbarga', 'STU007'),
                                             ('Hervé Nkoulou', 'STU008'),
                                             ('Isabelle Ewane', 'STU009'),
                                             ('Jean-Baptiste Tamba', 'STU010');

-- Insertion dans t_history
INSERT INTO t_history (hi_module, hi_identifier, hi_title, hi_description) VALUES
                                                                               ('StudentModule', 1, 'Création de compte', 'Le compte étudiant pour Alice Dupont a été créé avec succès.'),
                                                                               ('StudentModule', 2, 'Mise à jour du profil', 'Bruno Kamdem a modifié ses informations personnelles.'),
                                                                               ('StudentModule', 3, 'Suppression de compte', 'Le compte de Claire Nguema a été supprimé du système.'),
                                                                               ('StudentModule', 4, 'Ajout d’un étudiant', 'David Talla a été ajouté à la base de données.'),
                                                                               ('StudentModule', 5, 'Modification du code étudiant', 'Le code d’Emilie Kouam a été mis à jour.'),
                                                                               ('StudentModule', 6, 'Connexion réussie', 'Franck Njoya s’est connecté au portail étudiant.'),
                                                                               ('StudentModule', 7, 'Mot de passe réinitialisé', 'Grace Mbarga a réinitialisé son mot de passe.'),
                                                                               ('StudentModule', 8, 'Erreur de connexion', 'Hervé Nkoulou a échoué à se connecter trois fois de suite.'),
                                                                               ('StudentModule', 9, 'Mise à jour du profil', 'Isabelle Ewane a modifié son adresse e-mail.'),
                                                                               ('StudentModule', 10, 'Création de compte', 'Le compte de Jean-Baptiste Tamba a été créé et validé par un administrateur.');
