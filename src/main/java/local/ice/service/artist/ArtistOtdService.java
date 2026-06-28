package local.ice.service.artist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import local.ice.domain.artist.Artist;
import local.ice.repository.ArtistRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@AllArgsConstructor
public class ArtistOtdService {

    private final Queue<UUID> rotation = new ConcurrentLinkedQueue<>();
    private final Lock lock = new ReentrantLock(true);
    private final ArtistRepository repository;

    public Optional<Artist> pickNext() {
        if (rotation.isEmpty()) {
            lock.lock();
            try {
                if (rotation.isEmpty()) {
                    shuffle();
                }
            } finally {
                lock.unlock();
            }
        }
        int attempts = rotation.size();
        for (int i = 0; i < attempts; i++) {
            UUID id = rotation.poll();
            if (id == null) {
                lock.lock();
                try {
                    if (rotation.isEmpty()) {
                        shuffle();
                    }
                } finally {
                    lock.unlock();
                }
                continue;
            }
            Optional<Artist> artist = repository.findById(id);
            if (artist.isPresent()) {
                return artist;
            }
        }
        return Optional.empty();
    }

    private void shuffle() {
        List<UUID> ids = repository.findAllIds();
        Collections.shuffle(ids);
        rotation.addAll(ids);
    }

}
