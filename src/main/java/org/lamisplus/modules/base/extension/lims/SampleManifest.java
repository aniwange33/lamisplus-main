package org.lamisplus.modules.base.extension.lims;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.lamisplus.modules.base.util.converter.LocalDateConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@EqualsAndHashCode
public class SampleManifest  implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "manifest_id", nullable = false)
    private String manifestId;

    @Basic
    @Column(name = "sending_facility_id")
    private String sendingFacilityId ;
    @Basic
    @Column(name = "sending_facility_name")
    private String sendingFacilityName;

    @Basic
    @Column(name = "receiving_lab_id")
    private String receivingLabId;
    @Basic
    @Column(name = "receiving_lab_name")
    private String receivingLabName;

    @Basic
    @Column(name = "lab_number")
    private String labNumber;
    @Basic
    @Column(name = "sample_type")
    private String sampleType;

    @Basic
    @Column(name = "viral_load_indication")
    private String viralLoadIndication;

    @Basic
    @Column(name = "sample_ordered_by")
    private String sampleOrderedBy;
    @Basic
    @Column(name = "date_sample_ordered")
    @Convert(converter = LocalDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateSampleOrdered;
    @Basic
    @Column(name = "time_sample_ordered", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime timeSampleOrdered;
    @Basic
    @Column(name = "lab_order_priority")
    private String labOrderPriority;

    @Basic
    @Column(name = "sample_collected_by")
    private String sampleCollectedBy;
    @Basic
    @Column(name = "date_sample_collected")
    @Convert(converter = LocalDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateSampleCollected;
    @Basic
    @Column(name = "time_sample_collected", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime timeSampleCollected;

    @Basic
    @Column(name = "sample_transferred_by")
    private String sampleTransferredBy;

    @Basic
    @Column(name = "date_sample_transferred", nullable = true)
    @Convert(converter = LocalDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateSampleTransferred;
    @Basic
    @Column(name = "time_sample_transferred", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime timeSampleTransferred;

    @Basic
    @Column(name = "sample_disptached_by", nullable = true)
    private String sampleDispatchedBy;
    @Basic
    @Column(name = "date_sample_dispatched")
    @Convert(converter = LocalDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateSampleDispatched;
    @Basic
    @Column(name = "time_sample_dispatched", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime timeSampleDispatched;

    @Basic
    @Column(name = "courier_name")
    private String courierName;
    @Basic
    @Column(name = "courier_phone_number")
    private String courierPhoneNumber;

    @Basic
    @Column(name = "total_sample_shipment")
    private Integer totalSampleShipment;

    @Basic
    @Column(name = "dispatched")
    private Boolean dispatched;

   @Basic
    @Column(name = "patient_id", nullable = false)
    private Long clientId;

}