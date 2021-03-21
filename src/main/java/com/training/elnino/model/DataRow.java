package com.training.elnino.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "data_set", schema="elnino")
public class DataRow {
    public DataRow() {
    }

    @Id
    @Column(name = "obs", nullable = false)
    private long obs;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "month", nullable = false)
    private int month;
    @Column(name = "day", nullable = false)
    private int day;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "zon_winds", nullable = false)
    private double zonWinds;
    @Column(name = "mer_winds", nullable = false)
    private double merWinds;
    @Column(name = "humidity", nullable = false)
    private double humidity;
    @Column(name = "air_temp", nullable = false)
    private double airTemp;
    @Column(name = "s_s_temp", nullable = false)
    private double seaSurfaceTemp;

    @Version
    private Short version;

    public long getObs() {
        return obs;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getZonWinds() {
        return zonWinds;
    }

    public double getMerWinds() {
        return merWinds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRow dataRow = (DataRow) o;
        return obs == dataRow.obs &&
                year == dataRow.year &&
                month == dataRow.month &&
                day == dataRow.day &&
                Double.compare(dataRow.latitude, latitude) == 0 &&
                Double.compare(dataRow.longitude, longitude) == 0 &&
                Double.compare(dataRow.zonWinds, zonWinds) == 0 &&
                Double.compare(dataRow.merWinds, merWinds) == 0 &&
                Double.compare(dataRow.humidity, humidity) == 0 &&
                Double.compare(dataRow.airTemp, airTemp) == 0 &&
                Double.compare(dataRow.seaSurfaceTemp, seaSurfaceTemp) == 0 &&
                Objects.equals(date, dataRow.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obs, year, month, day, date, latitude, longitude, zonWinds, merWinds, humidity, airTemp, seaSurfaceTemp);
    }

    public void setObs(long obs) {
        this.obs = obs;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setZonWinds(double zonWinds) {
        this.zonWinds = zonWinds;
    }

    public void setMerWinds(double merWinds) {
        this.merWinds = merWinds;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setAirTemp(double airTemp) {
        this.airTemp = airTemp;
    }

    public void setsSeaSurfaceTemp(double sSTemp) {
        this.seaSurfaceTemp = sSTemp;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getAirTemp() {
        return airTemp;
    }

    public double getsSeaSurfaceTemp() {
        return seaSurfaceTemp;
    }
}
