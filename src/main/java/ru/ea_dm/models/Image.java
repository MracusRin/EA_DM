package ru.ea_dm.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "title")
    private String title;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    @Column(name = "download_link")
    private String downloadLink;

    @Column(name = "is_preview_image")
    private boolean isPreviewImage;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @PrePersist
    private void init() {
        uploadDate = LocalDateTime.now();
    }

}
