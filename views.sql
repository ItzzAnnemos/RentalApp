CREATE
MATERIALIZED VIEW accommodations_per_hosts AS
SELECT h.id        AS host_id,
       h.name      AS host_name,
       h.surname   AS host_surname,
       COUNT(a.id) AS num_accommodations
FROM host h
         LEFT JOIN accommodation a ON a.host_id = h.id
GROUP BY h.id, h.name, h.surname;

CREATE
MATERIALIZED VIEW hosts_per_countries AS
SELECT c.id        AS country_id,
       c.name      AS country_name,
       COUNT(h.id) AS num_hosts
FROM country c
         LEFT JOIN host h ON h.country_id = c.id
GROUP BY c.id;