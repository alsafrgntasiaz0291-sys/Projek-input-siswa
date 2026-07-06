-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2026 at 04:16 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projek01`
--

-- --------------------------------------------------------

--
-- Table structure for table `absensi`
--

CREATE TABLE `absensi` (
  `id_absensi` int(11) NOT NULL,
  `id_guru` int(11) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `jam_masuk` time DEFAULT NULL,
  `jam_keluar` time DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `keterangan` text DEFAULT NULL,
  `id_siswa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `absensi`
--

INSERT INTO `absensi` (`id_absensi`, `id_guru`, `tanggal`, `jam_masuk`, `jam_keluar`, `status`, `keterangan`, `id_siswa`) VALUES
(1, 1, '2026-07-06', '19:33:00', '19:33:00', 'Hadir', '', NULL),
(2, 4, '2026-07-06', '19:33:00', '19:33:00', 'Sakit', 'sakit flue', NULL),
(3, NULL, '2026-07-06', '19:46:00', '19:46:00', 'Hadir', '', 2);

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `id_guru` int(11) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `nama_guru` varchar(100) DEFAULT NULL,
  `jk` varchar(20) NOT NULL,
  `agama` varchar(20) NOT NULL,
  `notelp` varchar(20) NOT NULL,
  `email` varchar(25) NOT NULL,
  `TTL` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`id_guru`, `nip`, `nama_guru`, `jk`, `agama`, `notelp`, `email`, `TTL`, `alamat`) VALUES
(1, '0001', 'Siti Jubaedah', 'Perempuan', 'Islam', '08997865478', 'sitijubaedah@gmail.com', 'Depok/02-08-19978', 'Jl.bulak Raya'),
(2, '0002', 'Joko Sepriadi', 'Laki-laki', 'Islam', '08765438', 'jokowae09@gmail.com', 'semarang/08-09-1998', 'Jl.Buah belimbing'),
(4, '0003', 'Abdullah Amrih', 'Laki-laki', 'Islam', '08956427654', 'Abdullah897@gmail.com', 'Jakarta/06-12-1996', 'Jl.Rambutan'),
(5, '0004', 'ryo', 'Laki-laki', 'islam', '08756438019283', 'ryo@gmail.com', 'depok/02032001', 'Jl.Pancoran'),
(6, '0005', 'Amarina', 'Perempuan', 'Islam', '08765439876', 'Amira', 'Bogor/ 09-03-1999', 'JL.Raya Bogor'),
(7, '096785', 'Aminah', 'Perempuan', 'Islam', '0897654357', 'Aminah@gmail.com', 'Bogor/ 08-09-1991', 'Jln.Pinang');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal`
--

CREATE TABLE `jadwal` (
  `id` int(11) NOT NULL,
  `jurusan` varchar(100) NOT NULL,
  `kelas` varchar(100) NOT NULL,
  `hari` varchar(20) NOT NULL,
  `jam` varchar(30) NOT NULL,
  `mapel` varchar(100) NOT NULL,
  `guru` varchar(100) NOT NULL,
  `ruangan` varchar(50) NOT NULL,
  `keterangan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jadwal`
--

INSERT INTO `jadwal` (`id`, `jurusan`, `kelas`, `hari`, `jam`, `mapel`, `guru`, `ruangan`, `keterangan`) VALUES
(1, 'TKJ', 'X', 'Senin', '07:00-08:30', 'Matematika', 'Siti Jubaedah', 'R1', '-'),
(2, 'TKJ', 'X', 'Senin', '08:30-10:00', 'IPA', 'Joko Sepriadi', 'R2', '-'),
(3, 'TKJ', 'X', 'Selasa', '07:00-08:30', 'IPS', 'Abdullah Amrih', 'R1', '-'),
(4, 'TKJ', 'X', 'Selasa', '08:30-10:00', 'Bahasa Indonesia', 'Ryo', 'R3', '-'),
(5, 'Akuntansi', 'XI', 'Rabu', '07:00-08:30', 'Matematika', 'Siti Jubaedah', 'R1', '-'),
(6, 'Akuntansi', 'XI', 'Rabu', '08:30-10:00', 'IPA', 'Joko Sepriadi', 'R2', '-'),
(7, 'Perkantoran', 'XII', 'Kamis', '07:00-08:30', 'IPS', 'Abdullah Amrih', 'R3', '-'),
(8, 'Perkantoran', 'XII', 'Kamis', '08:30-10:00', 'Bahasa Indonesia', 'Ryo', 'R1', '-'),
(9, 'Pemasaran', 'A', 'Senin', '07.00', 'Bahasa Indonesia', 'Siti Jubaedah', 'Ruang 101', '');

-- --------------------------------------------------------

--
-- Table structure for table `jurusan`
--

CREATE TABLE `jurusan` (
  `id_jurusan` int(11) NOT NULL,
  `nama_jurusan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jurusan`
--

INSERT INTO `jurusan` (`id_jurusan`, `nama_jurusan`) VALUES
(1, 'perkantoran'),
(2, 'TKJ'),
(3, 'akp'),
(4, 'Pemasaran');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id_kelas` int(11) NOT NULL,
  `nama_kelas` varchar(20) DEFAULT NULL,
  `jurusan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id_kelas`, `nama_kelas`, `jurusan`) VALUES
(1, 'A', 'Akutansi'),
(6, 'X TKJ 1', 'TKJ'),
(7, 'X TKJ 2', 'TKJ'),
(8, 'XI Akuntansi 1', 'Akuntansi'),
(9, 'XI Akuntansi 2', 'Akuntansi'),
(10, 'XII Perkantoran 1', 'Perkantoran'),
(11, 'XII Perkantoran 2', 'Perkantoran');

-- --------------------------------------------------------

--
-- Table structure for table `mapel`
--

CREATE TABLE `mapel` (
  `id_mapel` int(11) NOT NULL,
  `nama_mapel` varchar(100) DEFAULT NULL,
  `kkm` int(11) DEFAULT NULL,
  `id_jurusan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mapel`
--

INSERT INTO `mapel` (`id_mapel`, `nama_mapel`, `kkm`, `id_jurusan`) VALUES
(1, 'Matematika', 75, NULL),
(2, 'IPA', 75, NULL),
(3, 'IPS', 75, NULL),
(4, 'Bahasa Indonesia', 75, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `id_nilai` int(11) NOT NULL,
  `id_siswa` int(11) DEFAULT NULL,
  `id_mapel` int(11) DEFAULT NULL,
  `uh` double DEFAULT NULL,
  `uts` double DEFAULT NULL,
  `uas` double DEFAULT NULL,
  `rata_rata` double DEFAULT NULL,
  `predikat` varchar(6) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `mata_plajaran` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`id_nilai`, `id_siswa`, `id_mapel`, `uh`, `uts`, `uas`, `rata_rata`, `predikat`, `keterangan`, `mata_plajaran`) VALUES
(1, 2, 1, 90, 88, 89, 89, 'A', 'LULUS', 'Matematika'),
(2, 4, 1, 80, 88, 78, 82, 'B', 'LULUS', 'Matematika');

-- --------------------------------------------------------

--
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `id` int(11) NOT NULL,
  `nama_ruangan` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`id`, `nama_ruangan`) VALUES
(1, 'Ruang 101'),
(2, 'Ruang 102'),
(3, 'Ruang 103'),
(4, 'Lab Komputer'),
(5, 'Lab IPA'),
(6, 'Perpustakaan'),
(7, 'Aula'),
(8, 'Ruang Guru');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id` int(11) NOT NULL,
  `nis` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jurusan` varchar(50) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `agama` varchar(20) NOT NULL,
  `JenisKelamin` varchar(20) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `id_jurusan` int(11) DEFAULT NULL,
  `id_kelas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id`, `nis`, `nama`, `jurusan`, `kelas`, `agama`, `JenisKelamin`, `Alamat`, `id_jurusan`, `id_kelas`) VALUES
(1, '8888', 'Aditia', 'Pemasaran', '12', 'Islam', 'Laki-laki', 'Jl.Sawah indah', NULL, NULL),
(2, '1111', 'Alsa', 'Perkantoran', '10', 'islam', 'Perempuan', 'Jl.Bulak Timur', NULL, NULL),
(3, '8888', 'Arif', 'Akuntansi', '11', 'Islam', 'Laki-laki', 'Jln.Hajidul', NULL, NULL),
(4, '9999', 'Amel', 'Perkantoran', '12', 'Islam', 'Perempuan', 'Jl.Haji bara', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absensi`
--
ALTER TABLE `absensi`
  ADD PRIMARY KEY (`id_absensi`),
  ADD KEY `id_guru` (`id_guru`),
  ADD KEY `id_siswa` (`id_siswa`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`id_guru`);

--
-- Indexes for table `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jurusan`
--
ALTER TABLE `jurusan`
  ADD PRIMARY KEY (`id_jurusan`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `mapel`
--
ALTER TABLE `mapel`
  ADD PRIMARY KEY (`id_mapel`),
  ADD KEY `id_jurusan` (`id_jurusan`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`id_nilai`),
  ADD KEY `id_siswa` (`id_siswa`),
  ADD KEY `id_mapel` (`id_mapel`);

--
-- Indexes for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_jurusan` (`id_jurusan`),
  ADD KEY `id_kelas` (`id_kelas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absensi`
--
ALTER TABLE `absensi`
  MODIFY `id_absensi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `guru`
--
ALTER TABLE `guru`
  MODIFY `id_guru` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `jadwal`
--
ALTER TABLE `jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `jurusan`
--
ALTER TABLE `jurusan`
  MODIFY `id_jurusan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id_kelas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `mapel`
--
ALTER TABLE `mapel`
  MODIFY `id_mapel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `nilai`
--
ALTER TABLE `nilai`
  MODIFY `id_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ruangan`
--
ALTER TABLE `ruangan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `absensi`
--
ALTER TABLE `absensi`
  ADD CONSTRAINT `absensi_ibfk_1` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`),
  ADD CONSTRAINT `absensi_ibfk_2` FOREIGN KEY (`id_siswa`) REFERENCES `siswa` (`id`);

--
-- Constraints for table `mapel`
--
ALTER TABLE `mapel`
  ADD CONSTRAINT `mapel_ibfk_1` FOREIGN KEY (`id_jurusan`) REFERENCES `jurusan` (`id_jurusan`);

--
-- Constraints for table `nilai`
--
ALTER TABLE `nilai`
  ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`id_siswa`) REFERENCES `siswa` (`id`),
  ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`id_mapel`) REFERENCES `mapel` (`id_mapel`);

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`id_jurusan`) REFERENCES `jurusan` (`id_jurusan`),
  ADD CONSTRAINT `siswa_ibfk_2` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id_kelas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
